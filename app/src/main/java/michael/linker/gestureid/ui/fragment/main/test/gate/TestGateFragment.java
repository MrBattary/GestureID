package michael.linker.gestureid.ui.fragment.main.test.gate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import michael.linker.gestureid.R;
import michael.linker.gestureid.ui.activity.ActivityGate;

public class TestGateFragment extends Fragment {
    private MaterialButton beginTestButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_test_gate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFields(view);
        initButtons();
    }

    private void initFields(final View view) {
        beginTestButton = view.findViewById(R.id.test_gate_start_button);
    }

    private void initButtons() {
        beginTestButton.setOnClickListener(l -> ActivityGate.moveToTestActivity(requireActivity()));
    }

}