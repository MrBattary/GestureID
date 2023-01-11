package michael.linker.gestureid.data.sensor.factory.composite;

import michael.linker.gestureid.config.sensor.SensorsConfiguration;
import michael.linker.gestureid.data.sensor.factory.ISensorFactory;
import michael.linker.gestureid.data.sensor.factory.SensorNotActivatedException;
import michael.linker.gestureid.data.sensor.factory.SensorNotFoundException;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.core.sensor.manager.AHardwareSensorManager;
import michael.linker.gestureid.core.sensor.sensor.SensorWrapper;

/**
 * Returns a Linear Acceleration sensor implementation
 */
public class LinearAccelerationSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = CompositeSensorType.LINEAR_ACCELERATION;
    private final AHardwareSensorManager sensorManager;
    private SensorWrapper linearAccelerationImplementation;

    public LinearAccelerationSensorFactory(final AHardwareSensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsConfiguration.Build.isLinearAccelerationDeactivated()) {
            throw new SensorNotActivatedException(
                    "The linear acceleration sensor is not activated!");
        } else {
            return this.getImplementation();
        }
    }

    @Override
    public SensorWrapper getImplementation() throws SensorNotFoundException {
        buildImplementation();
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

    private void buildImplementation() {
        if (linearAccelerationImplementation == null) {
            linearAccelerationImplementation =
                    new SensorWrapper(SENSOR_TYPE, sensorManager.getDefaultSensor(SENSOR_TYPE));
        }
    }
}
