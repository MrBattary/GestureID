package michael.linker.gestureid.event.buffer.overflow.strategy;

import java.util.Deque;

import michael.linker.gestureid.event.buffer.overflow.EventBufferOverflowException;
import michael.linker.gestureid.event.buffer.overflow.IEventBufferOverflowStrategy;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

public class EventBufferRaiseErrorStrategy implements IEventBufferOverflowStrategy {
    @Override
    public void execute(Deque<SynchronizedEvent> buffer) {
        throw new EventBufferOverflowException("Event buffer is overflowed!");
    }
}
