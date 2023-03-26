package michael.linker.gestureid.ui.fragment.test.rewind;

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

import michael.linker.gestureid.R;

public class TestRewindFragment extends Fragment {

    private TestRewindViewModel viewModel;

    public static TestRewindFragment newInstance() {
        return new TestRewindFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        NavController navController = NavHostFragment.findNavController(this);
        ViewModelStoreOwner viewModelStoreOwner = navController.getViewModelStoreOwner(
                R.id.navigation_test);
        viewModel = new ViewModelProvider(viewModelStoreOwner).get(TestRewindViewModel.class);

        return inflater.inflate(R.layout.fragment_test_rewind, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}