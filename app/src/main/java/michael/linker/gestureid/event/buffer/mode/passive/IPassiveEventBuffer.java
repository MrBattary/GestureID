package michael.linker.gestureid.event.buffer.mode.passive;

import michael.linker.gestureid.event.buffer.mode.IEventBuffer;
import michael.linker.gestureid.event.synchronizer.EventSynchronizerNotFoundException;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventListOfModels;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventSingleModel;

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
    SynchronizedEventSingleModel peekLastEvent() throws EventSynchronizerNotFoundException;

    /**
     * Returns and removes last event from the buffer.
     *
     * @return last event model.
     * @throws EventSynchronizerNotFoundException if buffer is empty.
     */
    SynchronizedEventSingleModel pullLastEvent() throws EventSynchronizerNotFoundException;

    /**
     * Returns and removes all events from the buffer.
     *
     * @return all event models.
     * @throws EventSynchronizerNotFoundException if buffer is empty.
     */
    SynchronizedEventListOfModels pullAllEventsFromBuffer()
            throws EventSynchronizerNotFoundException;
}
