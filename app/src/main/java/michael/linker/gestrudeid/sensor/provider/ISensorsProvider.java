package michael.linker.gestrudeid.sensor.provider;

import android.hardware.Sensor;

import java.util.List;

/**
 * Provider for sensors
 */
public interface ISensorsProvider {
    /**
     * Returns a sensor
     *
     * @param sensorType BaseSensorType or CompositeSensorType
     * @return Sensor implementation
     * @throws SensorsProviderNotFoundException If required sensor was not found
     */
    Sensor getSensor(Integer sensorType) throws SensorsProviderNotFoundException;

    /**
     * Returns a List of sensors according to the
     * list of activated sensors from the configuration file
     *
     * @return List of required sensors
     * @throws SensorsProviderNotFoundException If any required sensor was not found
     */
    List<Sensor> getActivatedSensors() throws SensorsProviderNotFoundException;

    /**
     * Returns a List of all available sensors which are enabled on the device
     *
     * @return List of all available sensors
     */
    List<Sensor> getSensors();
}
