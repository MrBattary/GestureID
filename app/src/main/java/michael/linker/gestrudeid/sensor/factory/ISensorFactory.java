package michael.linker.gestrudeid.sensor.factory;

import android.hardware.Sensor;

import michael.linker.gestrudeid.sensor.types.BaseSensorType;
import michael.linker.gestrudeid.sensor.types.CompositeSensorType;

/**
 * Sensor singleton factory
 */
public interface ISensorFactory {
    /**
     * Get activated implementation from the device sensor manager
     *
     * @return Implementation of a specific sensor
     * @throws SensorNotActivatedException If required sensor was not activated
     * @throws SensorNotFoundException     If any implementation was not found
     */
    Sensor getActivatedImplementation() throws SensorNotActivatedException, SensorNotFoundException;

    /**
     * Get implementation from the device sensor manager
     *
     * @return Implementation of a specific sensor
     * @throws SensorNotFoundException If any implementation was not found
     */
    Sensor getImplementation() throws SensorNotFoundException;

    /**
     * Get sensor type, it can be BaseSensorType or CompositeSensorType
     *
     * @return Sensor type ID
     * @see BaseSensorType
     * @see CompositeSensorType
     */
    Integer getSensorType();
}
