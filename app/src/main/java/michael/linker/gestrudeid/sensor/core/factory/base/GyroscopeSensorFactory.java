package michael.linker.gestrudeid.sensor.core.factory.base;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.core.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.core.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.core.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.BaseSensorType;
import michael.linker.gestrudeid.sensor.types.SensorType;

/**
 * Returns a Gyroscope implementation
 */
public class GyroscopeSensorFactory implements ISensorFactory {
    private static Sensor gyroscopeImplementation;

    public GyroscopeSensorFactory(final SensorManager sensorManager) {
        gyroscopeImplementation =
                sensorManager.getDefaultSensor(BaseSensorType.GYROSCOPE.toInt());
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
        return BaseSensorType.GYROSCOPE;
    }
}