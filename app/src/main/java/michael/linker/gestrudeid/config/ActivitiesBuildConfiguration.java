package michael.linker.gestrudeid.config;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import michael.linker.gestrudeid.BuildConfig;

/**
 * Wrapper for the activities build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestrudeid.elements.activity
 */
public class ActivitiesBuildConfiguration {
    private static LinkedList<Integer> buttonsOrder = null;

    public static LinkedList<Integer> getButtonsOrder() {
        if (buttonsOrder == null) {
            buttonsOrder = new LinkedList<>(Arrays.asList(BuildConfig.BUTTONS_ORDER));
        }
        return buttonsOrder;
    }
}
