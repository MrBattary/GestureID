package michael.linker.gestureid.event.accumulator.overflow;

import java.util.Map;

import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.event.accumulator.overflow.strategy.EventAccumulatorFlushOldestStrategy;
import michael.linker.gestureid.event.accumulator.overflow.strategy.EventAccumulatorFlushStrategy;
import michael.linker.gestureid.event.accumulator.overflow.strategy.EventAccumulatorRaiseErrorStrategy;

public class EventAccumulatorOverflowStrategyProvider {
    private static final IEventAccumulatorOverflowStrategy DEFAULT_STRATEGY;
    private static final Map<EventAccumulatorOverflowStrategy, IEventAccumulatorOverflowStrategy>
            STRATEGY_MAP;

    static {
        DEFAULT_STRATEGY = new EventAccumulatorRaiseErrorStrategy();
        STRATEGY_MAP = Map.of(
                EventAccumulatorOverflowStrategy.FLUSH, new EventAccumulatorFlushStrategy(),
                EventAccumulatorOverflowStrategy.FLUSH_OLDEST, new EventAccumulatorFlushOldestStrategy(),
                EventAccumulatorOverflowStrategy.RAISE_ERROR, DEFAULT_STRATEGY
        );
    }

    public static IEventAccumulatorOverflowStrategy getOverflowStrategy() {
        return STRATEGY_MAP.getOrDefault(
                EventAccumulatorConfiguration.Build.getAccumulatorOverflowStrategy(), DEFAULT_STRATEGY);
    }
}
