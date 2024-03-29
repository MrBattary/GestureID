package michael.linker.gestureid.data.event.accumulator.overflow;

import java.util.Queue;

import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;

/**
 * Abstract strategy.
 */
public interface IEventAccumulatorOverflowStrategy {
    /**
     * Execute the overflow strategy to the buffer.
     *
     * @param buffer buffer as deque.
     */
    void execute(Queue<SynchronizedEvent> buffer);
}
