package michael.linker.gestrudeid.sensor.factory;

import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.sensor.wrapper.sensor.SensorWrapper;

/**
 * Sensor singleton factory
 */
public interface ISensorFactory {
    /**
     * Get activated implementation from the device sensor manager
     *
     * @return Wrapper with implementation of a specific sensor
     * @throws SensorNotActivatedException If the required sensor was not activated
     * @throws SensorNotFoundException     If any implementation was not found
     */
    SensorWrapper getActivatedImplementation() throws SensorNotActivatedException, SensorNotFoundException;

    /**
     * Get implementation from the device sensor manager
     *
     * @return Wrapper with implementation of a specific sensor
     * @throws SensorNotFoundException If any implementation was not found
     */
    SensorWrapper getImplementation() throws SensorNotFoundException;

    /**
     * Get sensor type, it can be BaseSensorType or CompositeSensorType
     *
     * @return Sensor type
     * @see BaseSensorType
     * @see CompositeSensorType
     */
    SensorType getSensorType();
}
