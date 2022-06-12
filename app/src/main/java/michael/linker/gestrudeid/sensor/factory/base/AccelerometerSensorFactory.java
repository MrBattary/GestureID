package michael.linker.gestrudeid.sensor.factory.base;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.wrapper.sensor.SensorWrapper;

/**
 * Returns an Accelerometer implementation
 */
public class AccelerometerSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = BaseSensorType.ACCELEROMETER;
    private final ASensorManager sensorManager;
    private SensorWrapper accelerometerImplementation;

    public AccelerometerSensorFactory(final ASensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isAccelerometerDeactivated()) {
            throw new SensorNotActivatedException("The accelerometer is not activated!");
        } else {
            return getImplementation();
        }
    }

    @Override
    public SensorWrapper getImplementation() throws SensorNotFoundException {
        buildImplementation();
        if (accelerometerImplementation != null) {
            return accelerometerImplementation;
        } else {
            throw new SensorNotFoundException("An available accelerometer was not found!");
        }
    }

    @Override
    public SensorType getSensorType() {
        return SENSOR_TYPE;
    }

    private void buildImplementation() {
        if (accelerometerImplementation == null) {
            accelerometerImplementation =
                    new SensorWrapper(SENSOR_TYPE, sensorManager.getDefaultSensor(SENSOR_TYPE));
        }
    }
}
