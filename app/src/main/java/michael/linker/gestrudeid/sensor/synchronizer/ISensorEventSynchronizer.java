package michael.linker.gestrudeid.sensor.synchronizer;

import michael.linker.gestrudeid.sensor.model.SensorModel;

/**
 * Synchronizes sensor events from multiple SensorListeners
 */
public interface ISensorEventSynchronizer {
    /**
     * Registration of a sensor event by providing a model containing event data
     *
     * @param sensorModel Model with event data
     */
    void registerEvent(SensorModel sensorModel);
}
