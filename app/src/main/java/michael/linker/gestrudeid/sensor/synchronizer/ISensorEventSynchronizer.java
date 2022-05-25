package michael.linker.gestrudeid.sensor.synchronizer;

import java.util.List;

import michael.linker.gestrudeid.sensor.model.ASensorModel;
import michael.linker.gestrudeid.sensor.types.SensorType;

/**
 * Synchronizes sensor events from multiple SensorListeners
 */
public interface ISensorEventSynchronizer {
    /**
     * Registration of a sensor event by providing a model containing event data
     *
     * @param sensorModel Model with event data
     * @throws SensorEventSynchronizerNotFoundException If no place was found to register the event
     * @throws SensorEventSynchronizerFailedException   If it is not possible to register the event
     */
    void registerEvent(ASensorModel sensorModel)
    throws SensorEventSynchronizerNotFoundException, SensorEventSynchronizerFailedException;

    /**
     * Attach SensorListener by SensorType
     *
     * @param sensorType Sensor type as in Listener
     * @throws SensorEventSynchronizerFailedException If the Listener has already been attached
     */
    void attachOneListener(SensorType sensorType) throws SensorEventSynchronizerFailedException;

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
     * @throws SensorEventSynchronizerFailedException If any Listener has already been attached
     */
    void attachListenersList(List<SensorType> sensorTypesList)
            throws SensorEventSynchronizerFailedException;

    /**
     * Detach list of SensorListeners by SensorType
     *
     * @param sensorTypesList List of sensor types as in Listeners
     */
    void detachListenersList(List<SensorType> sensorTypesList);
}
