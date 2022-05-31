package michael.linker.gestrudeid.sensor.factory.base;

import android.hardware.Sensor;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;

/**
 * Returns a Gyroscope implementation
 */
public class GyroscopeSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = BaseSensorType.GYROSCOPE;
    private static Sensor gyroscopeImplementation;

    public GyroscopeSensorFactory(final ASensorManager sensorManager) {
        gyroscopeImplementation =
                sensorManager.getDefaultSensor(SENSOR_TYPE);
    }

    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isGyroscopeActivated()) {
            return this.getImplementation();
        } else {
            throw new SensorNotActivatedException("The gyroscope is not activated!");
        }
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (gyroscopeImplementation != null) {
            return gyroscopeImplementation;
        } else {
            throw new SensorNotFoundException("An available gyroscope was not found!");
        }
    }

    @Override
    public SensorType getSensorType() {
        return SENSOR_TYPE;
    }
}