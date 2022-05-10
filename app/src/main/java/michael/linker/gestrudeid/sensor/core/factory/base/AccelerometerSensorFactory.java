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
 * Returns an Accelerometer implementation
 */

public class AccelerometerSensorFactory implements ISensorFactory {
    private static Sensor accelerometerImplementation;

    public AccelerometerSensorFactory(final SensorManager sensorManager) {
        accelerometerImplementation =
                sensorManager.getDefaultSensor(BaseSensorType.ACCELEROMETER.toInt());
    }

    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isAccelerometerActivated()) {
            return this.getImplementation();
        } else {
            throw new SensorNotActivatedException("The accelerometer is not activated!");
        }
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (accelerometerImplementation != null) {
            return accelerometerImplementation;
        } else {
            throw new SensorNotFoundException("An available accelerometer was not found!");
        }
    }

    @Override
    public SensorType getSensorType() {
        return BaseSensorType.ACCELEROMETER;
    }
}
