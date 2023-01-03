package michael.linker.gestureid.sensor.listener.manager;

import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

/**
 * Manager that helps to work with sensor listeners
 */
public interface ISensorListenerManager {
    /**
     * Checks if a certain listener is registered
     *
     * @param sensorType Type of sensor for the listener
     * @return True if registered, false otherwise
     */
    Boolean isListenerRegistered(SensorType sensorType);

    /**
     * Register suppressed listener
     *
     * @param sensorType Type of sensor for the listener
     * @throws SensorListenerManagerFailedException If a listener with the same sensorType is
     * already registered
     */
    void registerListener(SensorType sensorType) throws SensorListenerManagerFailedException;

    /**
     * Unregister listener
     *
     * @param sensorType Type of sensor for the listener
     */
    void unregisterListener(SensorType sensorType);

    /**
     * Unregisters all listeners
     */
    void unregisterAllListeners();
}
