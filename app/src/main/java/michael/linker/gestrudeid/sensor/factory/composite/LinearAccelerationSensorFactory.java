package michael.linker.gestrudeid.sensor.factory.composite;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.wrapper.sensor.SensorWrapper;

/**
 * Returns a Linear Acceleration sensor implementation
 */
public class LinearAccelerationSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = CompositeSensorType.LINEAR_ACCELERATION;
    private final ASensorManager sensorManager;
    private SensorWrapper linearAccelerationImplementation;

    public LinearAccelerationSensorFactory(final ASensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isLinearAccelerationDeactivated()) {
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
