package michael.linker.gestrudeid.config;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import michael.linker.gestrudeid.BuildConfig;
import michael.linker.gestrudeid.elements.types.SwipeType;

/**
 * Wrapper for the activities build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestrudeid.elements.activity
 */
public class ActivitiesBuildConfiguration {
    private static Deque<Integer> buttonsOrder = null;
    private static Deque<SwipeType> swipesOrder = null;
    private static Deque<String> wordsOrder = null;

    public static Deque<Integer> getButtonsOrder() {
        if (buttonsOrder == null) {
            buttonsOrder = new LinkedList<>(Arrays.asList(BuildConfig.BUTTONS_ORDER));
        }
        return new LinkedList<>(buttonsOrder);
    }

    public static Deque<SwipeType> getSwipesOrder() {
        if (swipesOrder == null) {
            swipesOrder = new LinkedList<>(Arrays.asList(BuildConfig.SWIPES_ORDER));
        }
        return new LinkedList<>(swipesOrder);
    }

    public static String getSwipeUpAnimation() {
        return BuildConfig.SWIPE_UP_ANIMATION;
    }

    public static String getSwipeDownAnimation() {
        return BuildConfig.SWIPE_DOWN_ANIMATION;
    }

    public static String getSwipeLeftAnimation() {
        return BuildConfig.SWIPE_LEFT_ANIMATION;
    }

    public static String getSwipeRightAnimation() {
        return BuildConfig.SWIPE_RIGHT_ANIMATION;
    }

    public static String getZoomInAnimation() {
        return BuildConfig.ZOOM_IN_ANIMATION;
    }

    public static String getZoomOutAnimation() {
        return BuildConfig.ZOOM_OUT_ANIMATION;
    }

    public static float getSwipeAnimationSpeed() {
        return BuildConfig.SWIPE_ANIMATION_SPEED;
    }

    public static Deque<String> getWordsOrder() {
        if (wordsOrder == null) {
            wordsOrder = new LinkedList<>(Arrays.asList(BuildConfig.WORDS_ORDER));
        }
        return new LinkedList<>(wordsOrder);
    }
}
