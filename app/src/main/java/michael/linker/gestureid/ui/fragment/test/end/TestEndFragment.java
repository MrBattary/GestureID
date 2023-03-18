package michael.linker.gestureid.ui.fragment.test.end;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.button.MaterialButton;

import michael.linker.gestureid.R;
import michael.linker.gestureid.ui.activity.ActivityGate;

public class TestEndFragment extends Fragment {
    private MaterialButton endTestButton;

    // TODO: Use the ViewModel
    private TestEndViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        NavController navController = NavHostFragment.findNavController(this);
        ViewModelStoreOwner viewModelStoreOwner = navController.getViewModelStoreOwner(
                R.id.navigation_test);
        viewModel = new ViewModelProvider(viewModelStoreOwner).get(TestEndViewModel.class);

        return inflater.inflate(R.layout.fragment_test_end, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFields(view);
        initButtons();
    }

    private void initFields(final View view) {
        endTestButton = view.findViewById(R.id.test_end_finish_button);
    }

    private void initButtons() {
        endTestButton.setOnClickListener(
                l -> ActivityGate.moveToMainActivity(requireActivity()));
    }
}