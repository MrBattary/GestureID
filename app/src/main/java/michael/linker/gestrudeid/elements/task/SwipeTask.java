package michael.linker.gestrudeid.elements.task;

import android.app.Activity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import michael.linker.gestrudeid.R;
import michael.linker.gestrudeid.config.ActivitiesBuildConfiguration;
import michael.linker.gestrudeid.elements.types.SwipeType;

public class SwipeTask implements ITask {
    private final Deque<SwipeType> swipesOrder = ActivitiesBuildConfiguration.getSwipesOrder();
    private final Map<SwipeType, String> swipeAnimationsSources = new HashMap<>();
    private final LottieAnimationView animationContainer;
    private final Runnable finishMethod;

    public SwipeTask(final Activity activity, Runnable finishMethod) {
        animationContainer = activity.findViewById(R.id.swipe__animation);
        animationContainer.setSpeed(1.5f);
        this.finishMethod = finishMethod;
        initializeSwipeAnimationSources();
    }

    @Override
    public void start() {
        proceed();
    }

    @Override
    public void proceed() {
        if (!swipesOrder.isEmpty()) {
            String newAnimation = swipeAnimationsSources.get(swipesOrder.pollFirst());
            if (newAnimation != null) {
                animationContainer.setAnimation(newAnimation);
                animationContainer.playAnimation();
            }
        } else {
            finish();
        }
    }

    private void finish() {
        finishMethod.run();
    }

    private void initializeSwipeAnimationSources() {
        swipeAnimationsSources.put(SwipeType.UP,
                ActivitiesBuildConfiguration.getSwipeUpAnimation());
        swipeAnimationsSources.put(SwipeType.DOWN,
                ActivitiesBuildConfiguration.getSwipeDownAnimation());
        swipeAnimationsSources.put(SwipeType.LEFT,
                ActivitiesBuildConfiguration.getSwipeLeftAnimation());
        swipeAnimationsSources.put(SwipeType.RIGHT,
                ActivitiesBuildConfiguration.getSwipeRightAnimation());
        swipeAnimationsSources.put(SwipeType.ZOOM_IN,
                ActivitiesBuildConfiguration.getZoomInAnimation());
        swipeAnimationsSources.put(SwipeType.ZOOM_OUT,
                ActivitiesBuildConfiguration.getZoomOutAnimation());
    }
}
