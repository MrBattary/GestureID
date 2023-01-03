package michael.linker.gestureid.sensor.factory.base;

import michael.linker.gestureid.config.SensorsConfiguration;
import michael.linker.gestureid.sensor.factory.ISensorFactory;
import michael.linker.gestureid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestureid.sensor.factory.SensorNotFoundException;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.core.sensor.manager.AHardwareSensorManager;
import michael.linker.gestureid.core.sensor.sensor.SensorWrapper;

/**
 * Returns an Accelerometer implementation
 */
public class AccelerometerSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = BaseSensorType.ACCELEROMETER;
    private final AHardwareSensorManager sensorManager;
    private SensorWrapper accelerometerImplementation;

    public AccelerometerSensorFactory(final AHardwareSensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsConfiguration.Build.isAccelerometerDeactivated()) {
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
