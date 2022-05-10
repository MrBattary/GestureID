package michael.linker.gestrudeid.sensor.core.factory;

import android.hardware.Sensor;

import michael.linker.gestrudeid.sensor.types.SensorType;

/**
 * Default/stub sensor factory
 */
public class DefaultSensorFactory implements ISensorFactory {
    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        throw new SensorNotActivatedException("Default sensor is not activated");
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        throw new SensorNotFoundException("Default sensor was not found");
    }

    @Override
    public SensorType getSensorType() {
        return new SensorType(-1);
    }
}
