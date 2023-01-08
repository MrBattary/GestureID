package michael.linker.gestureid.event.accumulator.overflow.strategy;

import java.util.Queue;

import michael.linker.gestureid.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.event.accumulator.overflow.IEventAccumulatorOverflowStrategy;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

public class EventAccumulatorRaiseErrorStrategy implements IEventAccumulatorOverflowStrategy {
    @Override
    public void execute(Queue<SynchronizedEvent> buffer) {
        throw new EventAccumulatorOverflowException("Event buffer is overflowed!");
    }
}
