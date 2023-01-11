package michael.linker.gestureid.data.event.accumulator.mode.active;

/**
 * An active accumulator that stores events and distribute them on the flush() call.
 */
public interface IActiveFlushableEventAccumulator extends IActiveEventAccumulator {
    /**
     * Starts accumulate data to the internal buffer.
     */
    void startAccumulation();

    /**
     * Clears all data from the accumulator and notifies listeners with it.
     */
    void flush();
}
