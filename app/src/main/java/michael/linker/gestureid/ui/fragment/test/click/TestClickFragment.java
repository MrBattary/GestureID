package michael.linker.gestureid.ui.fragment.test.click;

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

public class TestClickFragment extends Fragment {

    private TestClickViewModel viewModel;

    public static TestClickFragment newInstance() {
        return new TestClickFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        NavController navController = NavHostFragment.findNavController(this);
        ViewModelStoreOwner viewModelStoreOwner = navController.getViewModelStoreOwner(
                R.id.navigation_test);
        viewModel = new ViewModelProvider(viewModelStoreOwner).get(TestClickViewModel.class);

        return inflater.inflate(R.layout.fragment_test_click, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}