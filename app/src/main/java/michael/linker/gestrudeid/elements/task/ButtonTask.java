package michael.linker.gestrudeid.elements.task;

import android.app.Activity;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import michael.linker.gestrudeid.R;
import michael.linker.gestrudeid.config.ActivitiesBuildConfiguration;

public class ButtonTask implements ITask {
    private final int activeColor;
    private final int inactiveColor;
    private final Map<Integer, Button> buttons = new HashMap<>();
    private final LinkedList<Integer> buttonsOrder = ActivitiesBuildConfiguration.getButtonsOrder();
    private final Runnable finishMethod;

    /**
     * Default constructor
     *
     * @param activity ButtonActivity or another activity that provides buttons and colors
     * @param finishMethod The function or lambda that will be called after completing this task
     */
    public ButtonTask(final Activity activity, final Runnable finishMethod) {
        this.finishMethod = finishMethod;
        this.activeColor = ContextCompat.getColor(activity, R.color.light_green);
        this.inactiveColor = ContextCompat.getColor(activity, R.color.white);
        initializeButtonsMap(activity);
    }

    @Override
    public void start() {
        proceed();
    }

    private void proceed() {
        if (!buttonsOrder.isEmpty()) {
            Button button = buttons.get(buttonsOrder.pollFirst());
            if(button != null) {
                button.setBackgroundColor(activeColor);
                button.setOnClickListener((view) -> {
                    button.setBackgroundColor(inactiveColor);
                    button.setOnClickListener(null);
                    proceed();
                });
            }
        } else {
            finish();
        }
    }

    private void finish() {
        finishMethod.run();
    }

    private void initializeButtonsMap(final Activity activity) {
        buttons.put(1, activity.findViewById(R.id.button__button_1));
        buttons.put(2, activity.findViewById(R.id.button__button_2));
        buttons.put(3, activity.findViewById(R.id.button__button_3));
        buttons.put(4, activity.findViewById(R.id.button__button_4));
        buttons.put(5, activity.findViewById(R.id.button__button_5));
        buttons.put(6, activity.findViewById(R.id.button__button_6));
        buttons.put(7, activity.findViewById(R.id.button__button_7));
        buttons.put(8, activity.findViewById(R.id.button__button_8));
        buttons.put(9, activity.findViewById(R.id.button__button_9));
        buttons.put(10, activity.findViewById(R.id.button__button_10));
        buttons.put(11, activity.findViewById(R.id.button__button_11));
        buttons.put(12, activity.findViewById(R.id.button__button_12));
        buttons.put(13, activity.findViewById(R.id.button__button_13));
        buttons.put(14, activity.findViewById(R.id.button__button_14));
        buttons.put(15, activity.findViewById(R.id.button__button_15));
    }
}
