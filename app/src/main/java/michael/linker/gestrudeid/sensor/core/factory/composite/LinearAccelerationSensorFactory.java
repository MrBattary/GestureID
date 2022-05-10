package michael.linker.gestrudeid.sensor.core.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.core.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.core.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.core.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.CompositeSensorType;
import michael.linker.gestrudeid.sensor.types.SensorType;

/**
 * Returns a Linear Acceleration sensor implementation
 */
public class LinearAccelerationSensorFactory implements ISensorFactory {
    private static Sensor linearAccelerationImplementation;

    public LinearAccelerationSensorFactory(final SensorManager sensorManager) {
        linearAccelerationImplementation = sensorManager.getDefaultSensor(
                CompositeSensorType.LINEAR_ACCELERATION.toInt());
    }

    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isLinearAccelerationActivated()) {
            return this.getImplementation();
        } else {
            throw new SensorNotActivatedException(
                    "The linear acceleration sensor is not activated");
        }
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (linearAccelerationImplementation != null) {
            return linearAccelerationImplementation;
        } else {
            throw new SensorNotFoundException(
                    "An available linear acceleration sensor was not found!");
        }
    }

    @Override
    public SensorType getSensorType() {
        return CompositeSensorType.LINEAR_ACCELERATION;
    }
}
