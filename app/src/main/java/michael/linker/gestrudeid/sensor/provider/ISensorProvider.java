package michael.linker.gestrudeid.sensor.provider;

import java.util.List;

import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.sensor.wrapper.sensor.SensorWrapper;

/**
 * Provider for sensors
 */
public interface ISensorProvider {
    /**
     * Returns a sensor
     *
     * @param sensorType BaseSensorType or CompositeSensorType
     * @return Wrapper with sensor implementation
     * @throws SensorProviderNotFoundException If required sensor was not found
     */
    SensorWrapper getSensor(SensorType sensorType) throws SensorProviderNotFoundException;

    /**
     * Returns a List of sensors according to the
     * list of activated sensors from the configuration file
     *
     * @return List of activated wrappers with sensors
     * @throws SensorProviderNotFoundException If any required sensor was not found
     */
    List<SensorWrapper> getActivatedSensors() throws SensorProviderNotFoundException;

    /**
     * Returns a List of all available sensors which are enabled on the device
     *
     * @return List of all available wrappers with sensors
     */
    List<SensorWrapper> getSensors();
}
