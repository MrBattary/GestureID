package michael.linker.gestureid.event.buffer.mode.active;

import michael.linker.gestureid.event.buffer.mode.IEventBuffer;

/**
 * An active buffer that performs certain actions when receiving new information.
 */
public interface IActiveEventBuffer extends IEventBuffer {
    /**
     * Subscribe listener to the buffer updates.
     *
     * @param listener active event buffer listener.
     */
    void subscribe(IActiveEventBufferListener listener);

    /**
     * Unsubscribe listener from the buffer updates.
     *
     * @param listener active event buffer listener.
     */
    void unsubscribe(IActiveEventBufferListener listener);

    /**
     * Unsubscribe all listeners.
     */
    void unsubscribeAll();
}
