package michael.linker.gestureid.event.accumulator.overflow.strategy;

import java.util.Deque;

import michael.linker.gestureid.event.accumulator.overflow.IEventAccumulatorOverflowStrategy;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

public class EventAccumulatorFlushOldestStrategy implements IEventAccumulatorOverflowStrategy {
    @Override
    public void execute(Deque<SynchronizedEvent> buffer) {
        if (buffer.size() > 0) {
            buffer.remove();
        }
    }
}