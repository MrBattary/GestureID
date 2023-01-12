package michael.linker.gestureid.config.event;

import michael.linker.gestureid.BuildConfig;
import michael.linker.gestureid.config.Configuration;
import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.config.ConfigurationType;
import michael.linker.gestureid.config.event.bean.ActiveEventDistributableAccumulatorBean;
import michael.linker.gestureid.config.event.bean.ActiveEventFlushableAccumulatorBean;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveFlushableEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.mode.active.impl.ActiveDistributableEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.mode.active.impl.ActiveFlushableEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.overflow.EventAccumulatorOverflowStrategy;

/**
 * Event buffer configuration
 *
 * @see michael.linker.gestureid.data.event.accumulator
 */
public final class EventAccumulatorConfiguration {
    private static ConfigurationBean<IActiveEventAccumulator> activeAccumulatorBean =
            null;

    public static IActiveEventAccumulator getActiveAccumulator() {
        if (activeAccumulatorBean == null) {
            activeAccumulatorBean = new ActiveEventDistributableAccumulatorBean(
                    new ActiveDistributableEventAccumulator());
        }
        if (activeAccumulatorBean.getImplementation() instanceof IActiveFlushableEventAccumulator) {
            return (IActiveFlushableEventAccumulator) activeAccumulatorBean;
        }
        return (IActiveEventAccumulator) activeAccumulatorBean;
    }

    public static void configure() {
        switch (Type.valueOf(Configuration.getConfiguration(ConfigurationType.EVENT_ACCUMULATOR))) {
            case ACTIVE_FLUSHABLE:
                activeAccumulatorBean = new ActiveEventFlushableAccumulatorBean(
                        new ActiveFlushableEventAccumulator());
                break;
            case ACTIVE_DISTRIBUTABLE:
            default:
                activeAccumulatorBean = new ActiveEventDistributableAccumulatorBean(
                        new ActiveDistributableEventAccumulator());
        }
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

    public enum Type {
        ACTIVE_FLUSHABLE,
        ACTIVE_DISTRIBUTABLE
    }
}
