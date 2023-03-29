package michael.linker.gestureid.ui.fragment.test.swipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;

import michael.linker.gestureid.R;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;

public class TestSwipeFragment extends Fragment {
    private Slider sliderSubtask1, sliderSubtask2, sliderSubtask3, sliderSubtask4First,
            sliderSubtask4Second, sliderSubtask5, sliderSubtask6, sliderSubtask7;
    private boolean sliderSubtask1Finished, sliderSubtask2Finished, sliderSubtask3Finished,
            sliderSubtask4Finished, sliderSubtask5Finished, sliderSubtask6Finished,
            sliderSubtask7Finished;
    private MaterialButton nextTaskButton;

    private IDialog startTaskDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_swipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initTasks();
    }

    private void initViews(View view) {
        sliderSubtask1 = view.findViewById(R.id.test_swipe_1_slider);
        sliderSubtask2 = view.findViewById(R.id.test_swipe_2_slider);
        sliderSubtask3 = view.findViewById(R.id.test_swipe_3_slider);
        sliderSubtask4First = view.findViewById(R.id.test_swipe_4_first_slider);
        sliderSubtask4Second = view.findViewById(R.id.test_swipe_4_second_slider);
        sliderSubtask5 = view.findViewById(R.id.test_swipe_5_slider);
        sliderSubtask6 = view.findViewById(R.id.test_swipe_6_slider);
        sliderSubtask7 = view.findViewById(R.id.test_swipe_7_slider);
        nextTaskButton = view.findViewById(R.id.test_swipe_next_task_button);
    }

    private void initTasks() {
        initFirstTask();
    }

    private void initFirstTask() {
        sliderSubtask1.addOnChangeListener((slider, value, fromUser) -> {
            if (value >= 100.0) {
                slider.setEnabled(false);
                sliderSubtask1Finished = true;
            }
        });
    }
}