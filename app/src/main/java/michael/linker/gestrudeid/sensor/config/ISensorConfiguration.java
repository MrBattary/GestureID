package michael.linker.gestrudeid.sensor.config;

import android.hardware.Sensor;

import java.util.List;

/**
 * Configurator for sensors
 */
public interface ISensorConfiguration {
    /**
     * Returns required sensor
     *
     * @param sensorType BaseSensorType or CompositeSensorType
     * @return Sensor implementation
     * @throws SensorConfigurationNotFoundException If required sensor was not found
     */
    Sensor getSensor(Integer sensorType) throws SensorConfigurationNotFoundException;

    /**
     * Returns a List of sensors according to the
     * list of required sensors from the configuration file
     *
     * @return List of required sensors
     * @throws SensorConfigurationNotFoundException If any required sensor was not found
     */
    List<Sensor> getRequiredSensors() throws SensorConfigurationNotFoundException;

    /**
     * Returns a List of all available sensors which are enabled on the device
     *
     * @return List of all available sensors
     */
    List<Sensor> getAvailableSensors();
}
