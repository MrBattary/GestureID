package michael.linker.gestureid.data.event.accumulator.overflow.strategy;

import java.util.Queue;

import michael.linker.gestureid.data.event.accumulator.overflow.IEventAccumulatorOverflowStrategy;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;

public class EventAccumulatorFlushStrategy implements IEventAccumulatorOverflowStrategy {
    @Override
    public void execute(Queue<SynchronizedEvent> buffer) {
        buffer.clear();
    }
}
