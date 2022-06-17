package michael.linker.gestrudeid.synchronizer;

import java.util.List;

import michael.linker.gestrudeid.sensor.model.ASensorModel;
import michael.linker.gestrudeid.sensor.type.SensorType;

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
     * Attach SensorListener by SensorType
     *
     * @param sensorType Sensor type as in Listener
     * @throws EventSynchronizerFailedException If the Listener has already been attached
     */
    void attachOneListener(SensorType sensorType) throws EventSynchronizerFailedException;

    /**
     * Detach listener by SensorType
     *
     * @param sensorType Sensor type as in Listener
     */
    void detachOneListener(SensorType sensorType);

    /**
     * Attach list of SensorListeners by SensorType
     *
     * @param sensorTypesList List of sensor types as in Listeners
     * @throws EventSynchronizerFailedException If any Listener has already been attached
     */
    void attachListenersList(List<SensorType> sensorTypesList)
            throws EventSynchronizerFailedException;

    /**
     * Detach list of SensorListeners by SensorType
     *
     * @param sensorTypesList List of sensor types as in Listeners
     */
    void detachListenersList(List<SensorType> sensorTypesList);
}
