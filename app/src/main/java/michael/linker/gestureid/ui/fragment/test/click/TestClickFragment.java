package michael.linker.gestureid.ui.fragment.test.click;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.test.TestConfiguration;
import michael.linker.gestureid.data.res.ColorsProvider;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceDialogModel;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceInfoDialog;

public class TestClickFragment extends Fragment {
    private final Queue<Integer> clickViewQueue = TestConfiguration.Build.getTestClickSequence();
    private final List<View> clickViewList = new ArrayList<>();
    private View activeClickView = null;

    private IDialog startTaskDialog, endTaskDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_click, container, false);
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
        clickViewList.add(view.findViewById(R.id.test_click_0));
        clickViewList.add(view.findViewById(R.id.test_click_1));
        clickViewList.add(view.findViewById(R.id.test_click_2));
        clickViewList.add(view.findViewById(R.id.test_click_3));
        clickViewList.add(view.findViewById(R.id.test_click_4));
        clickViewList.add(view.findViewById(R.id.test_click_5));
        clickViewList.add(view.findViewById(R.id.test_click_6));
        clickViewList.add(view.findViewById(R.id.test_click_7));
        clickViewList.add(view.findViewById(R.id.test_click_8));
        clickViewList.add(view.findViewById(R.id.test_click_9));
        clickViewList.add(view.findViewById(R.id.test_click_10));
        clickViewList.add(view.findViewById(R.id.test_click_11));
        clickViewList.add(view.findViewById(R.id.test_click_12));
        clickViewList.add(view.findViewById(R.id.test_click_13));
        clickViewList.add(view.findViewById(R.id.test_click_14));
    }

    private void initDialogs(final View view) {
        startTaskDialog = new SingleChoiceInfoDialog(requireContext(),
                new SingleChoiceDialogModel(
                        StringsProvider.getString(R.string.dialog_test_task_description_title),
                        StringsProvider.getString(R.string.test_click_task_description),
                        StringsProvider.getString(R.string.button_test_start_task)
                ),
                (dialogInterface, i) -> {
                    nextClickView();
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
                            R.id.navigation_action_test_click_to_test_swipe);
                });
    }

    private void nextClickView() {
        clearPreviousView();
        setNextView();
    }

    private void clearPreviousView() {
        if (activeClickView != null) {
            activeClickView.setOnClickListener(null);
            activeClickView.setBackgroundColor(
                    ColorsProvider.getColor(R.color.md_theme_light_onSecondary));
        }
    }

    private void setNextView() {
        if (!clickViewQueue.isEmpty()) {
            activeClickView = clickViewList.get(clickViewQueue.poll());
            activeClickView.setOnClickListener(l -> nextClickView());
            activeClickView.setBackgroundColor(
                    ColorsProvider.getColor(R.color.md_theme_light_secondary));
        } else {
            endTaskDialog.show();
        }
    }
}