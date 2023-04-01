package michael.linker.gestureid.config.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

import michael.linker.gestureid.BuildConfig;

/**
 * Configuration during test
 */
public final class TestConfiguration {
    public static final class Build {
        public static String getTestResultFolderPath() {
            return BuildConfig.TEST_RESULT_FOLDER_PATH;
        }

        public static Queue<Integer> getTestClickSequence() {
            return new LinkedList<>(
                    new ArrayList<>(Arrays.stream(BuildConfig.TEST_CLICK_SEQUENCE)
                            .boxed()
                            .collect(Collectors.toList())));
        }

        public static Queue<Integer> getTestButtonScrollSequence() {
            return new LinkedList<>(
                    new ArrayList<>(Arrays.stream(BuildConfig.TEST_BUTTON_SCROLL_SEQUENCE)
                            .boxed()
                            .collect(Collectors.toList())));
        }

        public static int getTestKeyboardNumberOfSquares() {
            return BuildConfig.TEST_KEYBOARD_NUMBER_OF_SQUARES;
        }

        public static int getTestKeyboardSquaresOnTheScreen() {
            return BuildConfig.TEST_KEYBOARD_SQUARES_ON_SCREEN;
        }
    }
}
