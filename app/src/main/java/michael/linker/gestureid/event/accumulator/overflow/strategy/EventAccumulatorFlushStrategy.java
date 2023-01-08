package michael.linker.gestureid.event.accumulator.overflow.strategy;

import java.util.Queue;

import michael.linker.gestureid.event.accumulator.overflow.IEventAccumulatorOverflowStrategy;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

public class EventAccumulatorFlushStrategy implements IEventAccumulatorOverflowStrategy {
    @Override
    public void execute(Queue<SynchronizedEvent> buffer) {
        buffer.clear();
    }
}
