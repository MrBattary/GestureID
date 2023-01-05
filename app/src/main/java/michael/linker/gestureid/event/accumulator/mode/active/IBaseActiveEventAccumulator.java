package michael.linker.gestureid.event.accumulator.mode.active;

public interface IBaseActiveEventAccumulator {
    /**
     * Subscribe listener to the accumulator updates.
     *
     * @param listener active event buffer listener.
     */
    void subscribe(IActiveEventAccumulatorListener listener);

    /**
     * Unsubscribe listener from the accumulator updates.
     *
     * @param listener active event accumulator listener.
     */
    void unsubscribe(IActiveEventAccumulatorListener listener);

    /**
     * Unsubscribe all listeners.
     */
    void unsubscribeAll();
}
