package michael.linker.gestureid.ui.fragment.test.keyboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textview.MaterialTextView;

import michael.linker.gestureid.R;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceDialogModel;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceInfoDialog;

public class TestKeyboardFragment extends Fragment {
    private MaterialTextView counterTextView;
    private FrameLayout keyboardZone;

    private IDialog startTaskDialog, endTaskDialog;

    private TestKeyboardViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        NavController navController = NavHostFragment.findNavController(this);
        ViewModelStoreOwner viewModelStoreOwner = navController.getViewModelStoreOwner(
                R.id.navigation_test);
        viewModel = new ViewModelProvider(viewModelStoreOwner).get(TestKeyboardViewModel.class);

        return inflater.inflate(R.layout.fragment_test_keyboard, container, false);
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
        counterTextView = view.findViewById(R.id.test_keyboard_counter);
        keyboardZone = view.findViewById(R.id.test_keyboard_zone);
    }

    private void initDialogs(final View view) {
        startTaskDialog = new SingleChoiceInfoDialog(requireContext(),
                new SingleChoiceDialogModel(
                        StringsProvider.getString(R.string.dialog_test_task_description_title),
                        StringsProvider.getString(R.string.test_keyboard_task_description),
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
                            R.id.navigation_action_test_keyboard_to_test_end);
                });
    }
}