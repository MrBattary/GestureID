package michael.linker.gestureid.sensor.listener.suppressor;

import java.util.Map;

import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

/**
 * The ListenerSuppressor helps filter data from sensor listeners by suppressing them,
 * this does not require their cancellation of registration via SensorManager
 */
public interface ISensorListenerSuppressor {
    /**
     * All listeners stop registering events
     */
    void suppressAllListeners();

    /**
     * All listeners start registering events
     */
    void unsuppressAllListeners();

    /**
     * Checks if all listeners are suppressed
     *
     * @return True if suppressed, false otherwise
     */
    boolean isAllListenersSuppressed();

    /**
     * Checks if a certain listener is suppressed
     *
     * @param sensorType Type of sensor for the listener
     * @return Suppression status
     * @throws SensorListenerSuppressorNotFoundException If the listener was not found
     */
    Boolean isListenerSuppressed(SensorType sensorType)
            throws SensorListenerSuppressorNotFoundException;

    /**
     * Suppresses the sensor listener by the provided sensor type
     *
     * @param sensorType Type of sensor for the listener
     * @throws SensorListenerSuppressorNotFoundException If the listener was not found
     */
    void suppressListener(SensorType sensorType) throws SensorListenerSuppressorNotFoundException;

    /**
     * Unsuppresses the sensor listener by the provided sensor type
     *
     * @param sensorType Type of sensor for the listener
     * @throws SensorListenerSuppressorNotFoundException If the listener was not found
     */
    void unsuppressListener(SensorType sensorType) throws SensorListenerSuppressorNotFoundException;

    /**
     * Provides a map for all registered listeners and their suppression status
     *
     * @return Map that contains type of listeners and their suppression status
     */
    Map<SensorType, Boolean> getListenersSuppressedStatus();

    /**
     * Make the listener susceptible to suppression
     * By default, listener is suppressed
     *
     * @param sensorType Listener sensor type
     */
    void registerListener(SensorType sensorType);
}
