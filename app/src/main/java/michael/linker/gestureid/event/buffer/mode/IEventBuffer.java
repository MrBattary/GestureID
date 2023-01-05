package michael.linker.gestureid.event.buffer.mode;

import michael.linker.gestureid.event.buffer.overflow.EventBufferOverflowException;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * Intermediate event storage for subsequent processing
 */
public interface IEventBuffer {
    /**
     * Store data about synchronized event from the all sensors.
     *
     * @param synchronizedEvent model that containing timestamp
     *                          and list of sensor event data.
     * @throws EventBufferOverflowException if the buffer is full.
     */
    void buffer(SynchronizedEvent synchronizedEvent)
            throws EventBufferOverflowException;

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
