package michael.linker.gestureid.data.event.accumulator.overflow.strategy;

import java.util.Queue;

import michael.linker.gestureid.data.event.accumulator.overflow.IEventAccumulatorOverflowStrategy;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;

public class EventAccumulatorFlushOldestStrategy implements IEventAccumulatorOverflowStrategy {
    @Override
    public void execute(Queue<SynchronizedEvent> buffer) {
        // Nothing due CircularFifoQueue is a first-in first-out queue with a fixed size that
        // replaces its oldest element if full.
    }
}