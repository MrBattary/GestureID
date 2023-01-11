package michael.linker.gestureid.data.event.accumulator.mode;

import michael.linker.gestureid.data.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;

/**
 * Intermediate event accumulator for subsequent processing
 */
public interface IEventAccumulator {
    /**
     * Store data about synchronized event from the all sensors.
     *
     * @param synchronizedEvent model that containing timestamp
     *                          and list of sensor event data.
     * @throws EventAccumulatorOverflowException if the accumulator is full.
     */
    void accumulate(SynchronizedEvent synchronizedEvent)
            throws EventAccumulatorOverflowException;

    /**
     * Returns buffer max size.
     *
     * @return max size.
     */
    int getMaxSize();

    /**
     * Returns buffer current size.
     *
     * @return current size.
     */
    int getSize();
}
