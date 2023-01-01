package michael.linker.gestureid.event.buffer.passive;

import michael.linker.gestureid.event.buffer.IEventBuffer;
import michael.linker.gestureid.event.synchronizer.EventSynchronizerNotFoundException;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventListOfModels;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventSingleModel;

/**
 * A passive buffer that simply stores data and does not take any active actions.
 */
public interface IPassiveEventBuffer extends IEventBuffer {
    /**
     * Returns last event from the buffer but does not removes it from the buffer.
     *
     * @return last event model.
     * @throws EventSynchronizerNotFoundException if buffer is empty.
     */
    SynchronizedEventSingleModel peekLastEvent() throws EventSynchronizerNotFoundException;

    /**
     * Returns last event from the buffer.
     *
     * @return last event model.
     * @throws EventSynchronizerNotFoundException if buffer is empty.
     */
    SynchronizedEventSingleModel popLastEvent() throws EventSynchronizerNotFoundException;

    /**
     * Returns all events from the buffer.
     *
     * @return all event models.
     * @throws EventSynchronizerNotFoundException if buffer is empty.
     */
    SynchronizedEventListOfModels popAllEventsFromBuffer()
            throws EventSynchronizerNotFoundException;
}
