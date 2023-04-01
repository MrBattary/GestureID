package michael.linker.gestureid.ui.fragment.test.scroll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.test.TestConfiguration;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceDialogModel;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceInfoDialog;

public class TestScrollFragment extends Fragment {
    private final Queue<Integer> clickButtonQueue =
            TestConfiguration.Build.getTestButtonScrollSequence();
    private final List<MaterialButton> scrollButtons = new ArrayList<>();
    private final List<MaterialTextView> scrollPointers = new ArrayList<>();
    private MaterialButton activeButton = null;

    private IDialog startTaskDialog, endTaskDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_scroll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFields(view);
        initDialogs(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        startTaskDialog.show();
    }

    private void initFields(final View view) {
        scrollButtons.add(view.findViewById(R.id.test_scroll_button_1));
        scrollButtons.add(view.findViewById(R.id.test_scroll_button_2));
        scrollButtons.add(view.findViewById(R.id.test_scroll_button_3));
        scrollButtons.add(view.findViewById(R.id.test_scroll_button_4));
        scrollButtons.add(view.findViewById(R.id.test_scroll_button_5));
        scrollPointers.add(view.findViewById(R.id.test_scroll_pointer_1));
        scrollPointers.add(view.findViewById(R.id.test_scroll_pointer_2));
        scrollPointers.add(view.findViewById(R.id.test_scroll_pointer_3));
        scrollPointers.add(view.findViewById(R.id.test_scroll_pointer_4));
    }

    private void initDialogs(final View view) {
        startTaskDialog = new SingleChoiceInfoDialog(requireContext(),
                new SingleChoiceDialogModel(
                        StringsProvider.getString(R.string.dialog_test_task_description_title),
                        StringsProvider.getString(R.string.test_scroll_task_description),
                        StringsProvider.getString(R.string.button_test_start_task)
                ),
                (dialogInterface, i) -> {
                    nextButton();
                    startTaskDialog.dismiss();
                });

        endTaskDialog = new SingleChoiceInfoDialog(requireContext(),
                new SingleChoiceDialogModel(
                        StringsProvider.getString(R.string.dialog_success_title),
                        StringsProvider.getString(R.string.dialog_test_task_completed_successfully),
                        StringsProvider.getString(R.string.button_test_next_task)
                ),
                (dialogInterface, i) -> {
                    endTaskDialog.dismiss();
                    Navigation.findNavController(view).navigate(
                            R.id.navigation_action_test_scroll_to_test_keyboard);
                });
    }

    private void nextButton() {
        disablePreviousButton();
        enableNextButton();
    }

    private void disablePreviousButton() {
        if (activeButton != null) {
            activeButton.setEnabled(false);
            activeButton.setOnClickListener(null);
        }
    }

    private void enableNextButton() {
        if (!clickButtonQueue.isEmpty()) {
            int nextButtonPosition = clickButtonQueue.poll();
            activeButton = scrollButtons.get(nextButtonPosition);
            activeButton.setOnClickListener(l -> nextButton());
            activeButton.setEnabled(true);

            for (int i = 0; i < scrollPointers.size(); i++) {
                MaterialTextView scrollPointer = scrollPointers.get(i);
                if (i < nextButtonPosition) {
                    scrollPointer.setText(
                            StringsProvider.getString(R.string.test_scroll_button_below));
                } else {
                    scrollPointer.setText(
                            StringsProvider.getString(R.string.test_scroll_button_above));
                }
            }
        } else {
            endTaskDialog.show();
        }
    }
}