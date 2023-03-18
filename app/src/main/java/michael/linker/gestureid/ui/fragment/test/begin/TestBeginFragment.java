package michael.linker.gestureid.ui.fragment.test.begin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;

import michael.linker.gestureid.R;

public class TestBeginFragment extends Fragment {
    private MaterialButton nextTestStageButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_test_begin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFields(view);
        initButtons(view);
    }


    private void initFields(final View view) {
        nextTestStageButton = view.findViewById(R.id.test_begin_next_button);
    }

    private void initButtons(final View view) {
        final NavController navController = Navigation.findNavController(view);

        nextTestStageButton.setOnClickListener(
                l -> navController.navigate(R.id.navigation_action_test_begin_to_test_end));
    }
}