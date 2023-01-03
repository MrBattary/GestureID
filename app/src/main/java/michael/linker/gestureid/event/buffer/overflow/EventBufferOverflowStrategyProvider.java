package michael.linker.gestureid.event.buffer.overflow;

import java.util.Map;

import michael.linker.gestureid.config.event.EventBufferConfiguration;
import michael.linker.gestureid.event.buffer.overflow.strategy.EventBufferFlushOldestStrategy;
import michael.linker.gestureid.event.buffer.overflow.strategy.EventBufferFlushStrategy;
import michael.linker.gestureid.event.buffer.overflow.strategy.EventBufferRaiseErrorStrategy;

public class EventBufferOverflowStrategyProvider {
    private static final IEventBufferOverflowStrategy DEFAULT_STRATEGY;
    private static final Map<EventBufferOverflowStrategy, IEventBufferOverflowStrategy>
            STRATEGY_MAP;

    static {
        DEFAULT_STRATEGY = new EventBufferRaiseErrorStrategy();
        STRATEGY_MAP = Map.of(
                EventBufferOverflowStrategy.FLUSH, new EventBufferFlushStrategy(),
                EventBufferOverflowStrategy.FLUSH_OLDEST, new EventBufferFlushOldestStrategy(),
                EventBufferOverflowStrategy.RAISE_ERROR, DEFAULT_STRATEGY
        );
    }

    public static IEventBufferOverflowStrategy getOverflowStrategy() {
        return STRATEGY_MAP.getOrDefault(
                EventBufferConfiguration.Build.getBufferOverflowStrategy(), DEFAULT_STRATEGY);
    }
}
