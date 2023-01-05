package michael.linker.gestureid.event.accumulator.mode.passive;

import java.util.List;

import michael.linker.gestureid.event.accumulator.mode.IEventAccumulator;
import michael.linker.gestureid.event.synchronizer.EventSynchronizerNotFoundException;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * A passive accumulator that simply stores data and does not take any active actions.
 */
public interface IPassiveEventAccumulator extends IEventAccumulator {
    /**
     * Returns, but does not remove last event from the accumulator.
     *
     * @return last event model.
     * @throws EventSynchronizerNotFoundException if accumulator is empty.
     */
    SynchronizedEvent peek() throws EventSynchronizerNotFoundException;

    /**
     * Returns and removes last event from the accumulator.
     *
     * @return last event model.
     * @throws EventSynchronizerNotFoundException if accumulator is empty.
     */
    SynchronizedEvent pull() throws EventSynchronizerNotFoundException;

    /**
     * Returns and removes all events from the accumulator.
     *
     * @return all event models.
     * @throws EventSynchronizerNotFoundException if accumulator is empty.
     */
    List<SynchronizedEvent> pullAll()
            throws EventSynchronizerNotFoundException;
}
