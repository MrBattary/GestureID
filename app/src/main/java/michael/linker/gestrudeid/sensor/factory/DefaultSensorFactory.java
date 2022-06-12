package michael.linker.gestrudeid.sensor.factory;

import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.sensor.wrapper.sensor.SensorWrapper;

/**
 * Default/stub sensor factory
 */
public class DefaultSensorFactory implements ISensorFactory {
    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        throw new SensorNotActivatedException("Default sensor is not activated");
    }

    @Override
    public SensorWrapper getImplementation() throws SensorNotFoundException {
        throw new SensorNotFoundException("Default sensor was not found");
    }

    @Override
    public SensorType getSensorType() {
        return new SensorType(-1, "Error Type");
    }
}
