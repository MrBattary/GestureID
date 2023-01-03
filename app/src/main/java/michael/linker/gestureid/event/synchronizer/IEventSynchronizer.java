package michael.linker.gestureid.event.synchronizer;

import java.util.List;

import michael.linker.gestureid.sensor.model.ASensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

/**
 * Synchronizes sensor events from multiple SensorListeners
 */
public interface IEventSynchronizer {
    /**
     * Registration of a sensor event by providing a model containing event data
     *
     * @param sensorModel Model with event data
     * @throws EventSynchronizerNotFoundException If no place was found to register the event
     */
    void registerEvent(ASensorModel sensorModel)
    throws EventSynchronizerNotFoundException, EventSynchronizerFailedException;

    /**
     * Attach listener by SensorType
     *
     * @param sensorType Sensor type as in Listener
     * @throws EventSynchronizerFailedException If the Listener has already been attached
     */
    void attach(SensorType sensorType) throws EventSynchronizerFailedException;

    /**
     * Detach listener by SensorType
     *
     * @param sensorType Sensor type as in Listener
     */
    void detach(SensorType sensorType);

    /**
     * Attach list of listeners by SensorType
     *
     * @param sensorTypesList List of sensor types as in Listeners
     * @throws EventSynchronizerFailedException If any Listener has already been attached
     */
    void attach(List<SensorType> sensorTypesList)
            throws EventSynchronizerFailedException;

    /**
     * Detach list of listeners by SensorType
     *
     * @param sensorTypesList List of sensor types as in Listeners
     */
    void detach(List<SensorType> sensorTypesList);

    /**
     * Detach all listeners
     */
    void detachAll();
}
