package michael.linker.gestureid.config.event;

import michael.linker.gestureid.BuildConfig;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveEventAccumulator;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveFlushableEventAccumulator;
import michael.linker.gestureid.event.accumulator.mode.active.impl.ActiveDistributableEventAccumulator;
import michael.linker.gestureid.event.accumulator.mode.active.impl.ActiveFlushableEventAccumulator;
import michael.linker.gestureid.event.accumulator.overflow.EventAccumulatorOverflowStrategy;

/**
 * Event buffer configuration
 *
 * @see michael.linker.gestureid.event.accumulator
 */
public final class EventAccumulatorConfiguration {
    private static IActiveEventAccumulator activeAccumulator = null;

    public static IActiveEventAccumulator getActiveAccumulator() {
        if (activeAccumulator == null) {
            activeAccumulator = new ActiveDistributableEventAccumulator();
        }
        return activeAccumulator;
    }

    public static IActiveEventAccumulator getDistributableActiveAccumulator() {
        if (!(activeAccumulator instanceof ActiveDistributableEventAccumulator)) {
            activeAccumulator = new ActiveDistributableEventAccumulator();
        }
        return activeAccumulator;
    }

    public static IActiveFlushableEventAccumulator getFlushableActiveAccumulator() {
        if (!(activeAccumulator instanceof ActiveFlushableEventAccumulator)) {
            activeAccumulator = new ActiveFlushableEventAccumulator();
        }
        return (IActiveFlushableEventAccumulator) activeAccumulator;
    }

    /**
     * Wrapper for the event accumulator build variables
     */
    public static final class Build {
        public static int getAccumulatorMaxSize() {
            return BuildConfig.EVENT_ACCUMULATOR_MAX_SIZE;
        }

        public static EventAccumulatorOverflowStrategy getAccumulatorOverflowStrategy() {
            return BuildConfig.EVENT_ACCUMULATOR_OVERFLOW_STRATEGY;
        }
    }
}
