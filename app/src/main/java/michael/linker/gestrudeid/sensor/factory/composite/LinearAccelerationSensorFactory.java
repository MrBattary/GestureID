package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;

/**
 * Returns a Linear Acceleration sensor implementation
 */
public class LinearAccelerationSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = CompositeSensorType.LINEAR_ACCELERATION;
    private static Sensor linearAccelerationImplementation;

    public LinearAccelerationSensorFactory(final ASensorManager sensorManager) {
        linearAccelerationImplementation = sensorManager.getDefaultSensor(SENSOR_TYPE);
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
        return SENSOR_TYPE;
    }
}
