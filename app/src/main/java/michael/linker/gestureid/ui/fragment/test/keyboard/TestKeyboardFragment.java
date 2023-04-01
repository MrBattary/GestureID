package michael.linker.gestureid.ui.fragment.test.keyboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textview.MaterialTextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.test.TestConfiguration;
import michael.linker.gestureid.core.sensor.sensor.type.SensorAxisType;
import michael.linker.gestureid.data.res.ColorsProvider;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceDialogModel;
import michael.linker.gestureid.ui.view.elementary.dialog.single.SingleChoiceInfoDialog;

public class TestKeyboardFragment extends Fragment {
    // Use SensorAxisType colors because they are fits well
    private static final int SCREEN_HEIGHT_RATIO = 100;
    private static final int KEYBOARD_ZONE_HEIGHT_RATIO = 40;
    private static final int INFO_ZONE_HEIGHT_RATIO =
            SCREEN_HEIGHT_RATIO - KEYBOARD_ZONE_HEIGHT_RATIO;

    private static final List<SensorAxisType> COLORS = List.of(SensorAxisType.values());
    private static final int ROW_SQUARE_ID_MULTIPLIER = 100;
    private static final int COLUMNS_KEYBOARD_ZONE = 11;
    private static final int ROWS_KEYBOARD_ZONE = 5;

    private Random randomGenerator;
    private int spawnElementWidth, spawnElementHeight;
    private int squaresLeft = TestConfiguration.Build.getTestKeyboardNumberOfSquares();
    private int squaresOnTheScreen;
    private final List<View> spawnedViewList = new LinkedList<>();

    private RelativeLayout testKeyboard, keyboardInfo;
    private FrameLayout keyboardZone;
    private MaterialTextView counterTextView;
    private IDialog startTaskDialog, endTaskDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
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
        testKeyboard = view.findViewById(R.id.test_keyboard);
        keyboardZone = view.findViewById(R.id.test_keyboard_zone);
        keyboardInfo = view.findViewById(R.id.test_keyboard_info);
        counterTextView = view.findViewById(R.id.test_keyboard_counter);
    }

    private void initDialogs(final View view) {
        startTaskDialog = new SingleChoiceInfoDialog(requireContext(),
                new SingleChoiceDialogModel(
                        StringsProvider.getString(R.string.dialog_test_task_description_title),
                        StringsProvider.getString(R.string.test_keyboard_task_description),
                        StringsProvider.getString(R.string.button_test_start_task)
                ),
                (dialogInterface, i) -> {
                    startTask();
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

    private void startTask() {
        initLayout();
        for (int i = 0; i < TestConfiguration.Build.getTestKeyboardSquaresOnTheScreen(); i++) {
            spawnNextSquare();
        }
    }

    private void initLayout() {
        randomGenerator = new Random();
        setLayoutsHeights();
    }

    private void setLayoutsHeights() {
        int screenHeightRatioPart = testKeyboard.getHeight() / SCREEN_HEIGHT_RATIO;
        keyboardInfo.getLayoutParams().height = INFO_ZONE_HEIGHT_RATIO * screenHeightRatioPart;
        keyboardInfo.requestLayout();

        int keyboardZoneHeight = KEYBOARD_ZONE_HEIGHT_RATIO * screenHeightRatioPart;
        keyboardZone.getLayoutParams().height = keyboardZoneHeight;
        keyboardZone.requestLayout();

        spawnElementWidth = keyboardZone.getWidth() / COLUMNS_KEYBOARD_ZONE;
        spawnElementHeight = keyboardZoneHeight / ROWS_KEYBOARD_ZONE;
    }

    private void spawnNextSquare() {
        if ((squaresOnTheScreen + squaresLeft) > 0) {
            if (squaresLeft > 0) {
                squaresLeft--;
                squaresOnTheScreen++;
                drawNewSquare();
            }
        } else {
            endTaskDialog.show();
        }
        counterTextView.setText(String.valueOf(squaresOnTheScreen + squaresLeft));
    }

    private void drawNewSquare() {
        View newView = new View(requireContext());

        setLayoutParamsToNewView(newView);
        addOnClickListenerToNewView(newView);
        pickRandomBackgroundColorForNewView(newView);

        spawnedViewList.add(newView);
        keyboardZone.addView(newView);
    }

    private void setLayoutParamsToNewView(View newView) {
        boolean isViewWithSameIdExists;
        int newViewId, newViewRow, newViewColumn;
        do {
            isViewWithSameIdExists = false;
            newViewRow = randomGenerator.nextInt(ROWS_KEYBOARD_ZONE);
            newViewColumn = randomGenerator.nextInt(COLUMNS_KEYBOARD_ZONE);
            newViewId = (ROW_SQUARE_ID_MULTIPLIER * newViewRow) + newViewColumn;

            for (View existView : spawnedViewList) {
                if (existView.getId() == newViewId) {
                    isViewWithSameIdExists = true;
                    break;
                }
            }
        } while (isViewWithSameIdExists);
        newView.setId(newViewId);
        FrameLayout.LayoutParams layoutParams =
                new FrameLayout.LayoutParams(spawnElementWidth, spawnElementHeight);
        layoutParams.setMargins(newViewColumn * spawnElementWidth,
                newViewRow * spawnElementHeight, 0, 0);
        newView.setLayoutParams(layoutParams);
    }

    private void addOnClickListenerToNewView(View newView) {
        newView.setOnClickListener(view -> {
            spawnedViewList.remove(view);
            keyboardZone.removeView(view);
            squaresOnTheScreen--;
            spawnNextSquare();
        });
    }

    private void pickRandomBackgroundColorForNewView(View newView) {
        int randomColorPointer = randomGenerator.nextInt(SensorAxisType.values().length);
        int randomColor = ColorsProvider.Axis.getColorForAxis(
                COLORS.get(randomColorPointer));
        newView.setBackgroundColor(randomColor);
    }
}