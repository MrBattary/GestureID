package michael.linker.gestureid.event.accumulator.mode.active;

import michael.linker.gestureid.event.accumulator.mode.IEventAccumulator;

/**
 * An active accumulator that performs certain actions when receiving new information.
 */
public interface IActiveEventAccumulator extends IEventAccumulator {
    /**
     * Subscribe listener to the accumulator updates.
     *
     * @param listener active event buffer listener.
     */
    void subscribe(IActiveEventBufferListener listener);

    /**
     * Unsubscribe listener from the accumulator updates.
     *
     * @param listener active event accumulator listener.
     */
    void unsubscribe(IActiveEventBufferListener listener);

    /**
     * Unsubscribe all listeners.
     */
    void unsubscribeAll();
}
