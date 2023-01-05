package michael.linker.gestureid.event.accumulator.overflow.strategy;

import java.util.Deque;

import michael.linker.gestureid.event.accumulator.overflow.IEventAccumulatorOverflowStrategy;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

public class EventAccumulatorFlushStrategy implements IEventAccumulatorOverflowStrategy {
    @Override
    public void execute(Deque<SynchronizedEvent> buffer) {
        buffer.clear();
    }
}
