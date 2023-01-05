package michael.linker.gestureid.event.buffer.mode.passive;

import java.util.List;

import michael.linker.gestureid.event.buffer.mode.IEventBuffer;
import michael.linker.gestureid.event.synchronizer.EventSynchronizerNotFoundException;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * A passive buffer that simply stores data and does not take any active actions.
 */
public interface IPassiveEventBuffer extends IEventBuffer {
    /**
     * Returns, but does not remove last event from the buffer.
     *
     * @return last event model.
     * @throws EventSynchronizerNotFoundException if buffer is empty.
     */
    SynchronizedEvent peek() throws EventSynchronizerNotFoundException;

    /**
     * Returns and removes last event from the buffer.
     *
     * @return last event model.
     * @throws EventSynchronizerNotFoundException if buffer is empty.
     */
    SynchronizedEvent pull() throws EventSynchronizerNotFoundException;

    /**
     * Returns and removes all events from the buffer.
     *
     * @return all event models.
     * @throws EventSynchronizerNotFoundException if buffer is empty.
     */
    List<SynchronizedEvent> pullAll()
            throws EventSynchronizerNotFoundException;
}
