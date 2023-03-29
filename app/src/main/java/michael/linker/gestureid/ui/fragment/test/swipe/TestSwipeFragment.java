package michael.linker.gestureid.ui.fragment.test.swipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.slider.Slider;

import java.text.NumberFormat;

import michael.linker.gestureid.R;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceDialogModel;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceInfoDialog;

public class TestSwipeFragment extends Fragment {
    private Slider sliderSubtask1, sliderSubtask2, sliderSubtask3, sliderSubtask4First,
            sliderSubtask4Second, sliderSubtask5, sliderSubtask6, sliderSubtask7;
    private CheckBox sliderSubtask1Checkbox, sliderSubtask2Checkbox, sliderSubtask3Checkbox,
            sliderSubtask4Checkbox, sliderSubtask5Checkbox, sliderSubtask6Checkbox,
            sliderSubtask7Checkbox;

    private IDialog startTaskDialog, endTaskDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_swipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initDialogs(view);
        initSubtasks();
    }

    @Override
    public void onStart() {
        super.onStart();
        startTaskDialog.show();
    }

    private void initViews(View view) {
        sliderSubtask1Checkbox = view.findViewById(R.id.test_swipe_1_checkbox);
        sliderSubtask2Checkbox = view.findViewById(R.id.test_swipe_2_checkbox);
        sliderSubtask3Checkbox = view.findViewById(R.id.test_swipe_3_checkbox);
        sliderSubtask4Checkbox = view.findViewById(R.id.test_swipe_4_checkbox);
        sliderSubtask5Checkbox = view.findViewById(R.id.test_swipe_5_checkbox);
        sliderSubtask6Checkbox = view.findViewById(R.id.test_swipe_6_checkbox);
        sliderSubtask7Checkbox = view.findViewById(R.id.test_swipe_7_checkbox);

        sliderSubtask1 = view.findViewById(R.id.test_swipe_1_slider);
        sliderSubtask2 = view.findViewById(R.id.test_swipe_2_slider);
        sliderSubtask3 = view.findViewById(R.id.test_swipe_3_slider);
        sliderSubtask4First = view.findViewById(R.id.test_swipe_4_first_slider);
        sliderSubtask4Second = view.findViewById(R.id.test_swipe_4_second_slider);
        sliderSubtask5 = view.findViewById(R.id.test_swipe_5_slider);
        sliderSubtask6 = view.findViewById(R.id.test_swipe_6_slider);
        sliderSubtask7 = view.findViewById(R.id.test_swipe_7_slider);
    }

    private void initDialogs(final View view) {
        startTaskDialog = new SingleChoiceInfoDialog(requireContext(),
                new SingleChoiceDialogModel(
                        StringsProvider.getString(R.string.dialog_test_task_description_title),
                        StringsProvider.getString(R.string.test_swipe_task_description),
                        StringsProvider.getString(R.string.button_test_start_task)
                ),
                (dialogInterface, i) -> {
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
                            R.id.navigation_action_test_swipe_to_test_scroll);
                });
    }

    private void initSubtasks() {
        initSubtask1();
        initSubtask2();
        initSubtask3();
        initSubtask4();
        initSubtask5();
        initSubtask6();
        initSubtask7();
    }

    private void initSubtask1() {
        sliderSubtask1.addOnChangeListener((slider, value, fromUser) -> {
            if (value >= 100.0) {
                slider.setEnabled(false);
                sliderSubtask1Checkbox.setChecked(true);
                checkIfAllSubtasksAreDone();
            }
        });
    }

    private void initSubtask2() {
        sliderSubtask2.addOnChangeListener((slider, value, fromUser) -> {
            if (value <= 0.0) {
                slider.setEnabled(false);
                sliderSubtask2Checkbox.setChecked(true);
                checkIfAllSubtasksAreDone();
            }
        });
    }

    private void initSubtask3() {
        sliderSubtask3.addOnChangeListener((slider, value, fromUser) -> {
            if (48.0 <= value && value <= 52.0) {
                slider.setEnabled(false);
                sliderSubtask3Checkbox.setChecked(true);
                checkIfAllSubtasksAreDone();
            }
        });
    }

    private void initSubtask4() {
        sliderSubtask4First.addOnChangeListener((slider, value, fromUser) -> {
            float secondSliderValue = sliderSubtask4Second.getValue();
            if (secondSliderValue - 2.0 <= value && value <= secondSliderValue + 2.0) {
                slider.setEnabled(false);
                sliderSubtask4Second.setEnabled(false);
                sliderSubtask4Checkbox.setChecked(true);
                checkIfAllSubtasksAreDone();
            }
        });
        sliderSubtask4Second.addOnChangeListener((slider, value, fromUser) -> {
            float firstSliderValue = sliderSubtask4First.getValue();
            if (firstSliderValue - 2.0 <= value && value <= firstSliderValue + 2.0) {
                slider.setEnabled(false);
                sliderSubtask4First.setEnabled(false);
                sliderSubtask4Checkbox.setChecked(true);
                checkIfAllSubtasksAreDone();
            }
        });
    }

    private void initSubtask5() {
        sliderSubtask5.addOnChangeListener((slider, value, fromUser) -> {
            if (10.0 <= value && value <= 20.0) {
                slider.setEnabled(false);
                sliderSubtask5Checkbox.setChecked(true);
                checkIfAllSubtasksAreDone();
            }
        });
        sliderSubtask5.setLabelFormatter(value -> {
            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            numberFormat.setMaximumFractionDigits(0);
            return numberFormat.format(value);
        });
    }

    private void initSubtask6() {
        sliderSubtask6.addOnChangeListener((slider, value, fromUser) -> {
            if (value >= 75.0) {
                slider.setEnabled(false);
                sliderSubtask6Checkbox.setChecked(true);
                checkIfAllSubtasksAreDone();
            }
        });
        sliderSubtask6.setLabelFormatter(value -> {
            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            numberFormat.setMaximumFractionDigits(0);
            return numberFormat.format(value);
        });
    }

    private void initSubtask7() {
        sliderSubtask7.addOnChangeListener((slider, value, fromUser) -> {
            if (value <= 25.0) {
                slider.setEnabled(false);
                sliderSubtask7Checkbox.setChecked(true);
                checkIfAllSubtasksAreDone();
            }
        });
        sliderSubtask7.setLabelFormatter(value -> {
            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            numberFormat.setMaximumFractionDigits(0);
            return numberFormat.format(value);
        });
    }

    private void checkIfAllSubtasksAreDone() {
        if (sliderSubtask1Checkbox.isChecked() &&
                sliderSubtask2Checkbox.isChecked() &&
                sliderSubtask3Checkbox.isChecked() &&
                sliderSubtask4Checkbox.isChecked() &&
                sliderSubtask5Checkbox.isChecked() &&
                sliderSubtask6Checkbox.isChecked() &&
                sliderSubtask7Checkbox.isChecked()) {
            endTaskDialog.show();
        }
    }
}