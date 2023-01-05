package michael.linker.gestureid.sensor.factory.base;

import michael.linker.gestureid.config.sensor.SensorsConfiguration;
import michael.linker.gestureid.sensor.factory.ISensorFactory;
import michael.linker.gestureid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestureid.sensor.factory.SensorNotFoundException;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.core.sensor.manager.AHardwareSensorManager;
import michael.linker.gestureid.core.sensor.sensor.SensorWrapper;

/**
 * Returns a Gyroscope implementation
 */
public class GyroscopeSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = BaseSensorType.GYROSCOPE;
    private final AHardwareSensorManager sensorManager;
    private SensorWrapper gyroscopeImplementation;

    public GyroscopeSensorFactory(final AHardwareSensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsConfiguration.Build.isGyroscopeDeactivated()) {
            throw new SensorNotActivatedException("The gyroscope is not activated!");
        } else {
            return getImplementation();
        }
    }

    @Override
    public SensorWrapper getImplementation() throws SensorNotFoundException {
        buildImplementation();
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

    private void buildImplementation() {
        if (gyroscopeImplementation == null) {
            gyroscopeImplementation =
                    new SensorWrapper(SENSOR_TYPE, sensorManager.getDefaultSensor(SENSOR_TYPE));
        }
    }
}