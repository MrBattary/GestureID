package michael.linker.gestureid.event.buffer.mode;

import michael.linker.gestureid.event.buffer.overflow.EventBufferOverflowException;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventListOfModels;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventSingleModel;

/**
 * Intermediate event storage for subsequent processing
 */
public interface IEventBuffer {
    /**
     * Store data about synchronized event from the single sensor.
     *
     * @param synchronizedSensorModel model containing timestamp and sensor event data.
     * @throws EventBufferOverflowException if the buffer is full.
     */
    void buffer(SynchronizedEventSingleModel synchronizedSensorModel)
            throws EventBufferOverflowException;

    /**
     * Store data about synchronized event from the all sensors.
     *
     * @param synchronizedSensorModels model that containing timestamp
     *                                 and list of sensor event data.
     * @throws EventBufferOverflowException if the buffer is full.
     */
    void buffer(SynchronizedEventListOfModels synchronizedSensorModels)
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
