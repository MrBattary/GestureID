package michael.linker.gestureid.event.buffer.overflow;

import java.util.Deque;

import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * Abstract strategy.
 */
public interface IEventBufferOverflowStrategy {
    /**
     * Execute the overflow strategy to the buffer.
     *
     * @param buffer buffer as deque.
     */
    void execute(Deque<SynchronizedEvent> buffer);
}
