package michael.linker.gestureid.ui.fragment.playground.root;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import michael.linker.gestureid.R;
import michael.linker.gestureid.data.system.SystemMode;
import michael.linker.gestureid.ui.activity.ActivityGate;
import michael.linker.gestureid.ui.activity.intent.playground.PlaygroundSettingsParcelable;

public class PlaygroundFragment extends Fragment {
    private MaterialButtonToggleGroup modeToggleGroup;
    private MaterialButton enterButton;
    private PlaygroundSettingsParcelable playgroundSettings;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playground, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playgroundSettings = new PlaygroundSettingsParcelable();

        initViews(view);
        initButtonLogic(view);
    }

    private void initViews(View view) {
        modeToggleGroup = view.findViewById(R.id.playground_controls_mode_toggle_group);
        enterButton = view.findViewById(R.id.playground_controls_enter_button);
    }

    private void initButtonLogic(View view) {
        modeToggleGroup.addOnButtonCheckedListener((toggleButton, checkedId, isChecked) -> {
            if (isChecked) {
                enterButton.setEnabled(true);
                int index = toggleButton.indexOfChild(view.findViewById(checkedId));
                switch (index) {
                    case 0:
                        playgroundSettings.setMode(SystemMode.LEARNING);
                        break;
                    case 1:
                        playgroundSettings.setMode(SystemMode.CONTROL);
                        break;
                    default:
                        break;
                }
            }
        });

        enterButton.setOnClickListener(l -> {
            ActivityGate.moveToPlaygroundActivity(requireActivity(), playgroundSettings);
        });
    }
}