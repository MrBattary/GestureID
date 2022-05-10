package michael.linker.gestrudeid.sensor.provider;

import android.hardware.Sensor;

import java.util.List;

import michael.linker.gestrudeid.sensor.types.SensorType;

/**
 * Provider for sensors
 */
public interface ISensorProvider {
    /**
     * Returns a sensor
     *
     * @param sensorType BaseSensorType or CompositeSensorType
     * @return Sensor implementation
     * @throws SensorProviderNotFoundException If required sensor was not found
     */
    Sensor getSensor(SensorType sensorType) throws SensorProviderNotFoundException;

    /**
     * Returns a List of sensors according to the
     * list of activated sensors from the configuration file
     *
     * @return List of required sensors
     * @throws SensorProviderNotFoundException If any required sensor was not found
     */
    List<Sensor> getActivatedSensors() throws SensorProviderNotFoundException;

    /**
     * Returns a List of all available sensors which are enabled on the device
     *
     * @return List of all available sensors
     */
    List<Sensor> getSensors();
}
