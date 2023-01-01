package michael.linker.gestureid.event.buffer;

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
     */
    void buffer(SynchronizedEventSingleModel synchronizedSensorModel);

    /**
     * Store data about synchronized event from the all sensors.
     *
     * @param synchronizedSensorModels model that containing timestamp
     *                                 and list of sensor event data.
     */
    void buffer(SynchronizedEventListOfModels synchronizedSensorModels);

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
