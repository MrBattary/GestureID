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
    private final ASensorManager sensorManager;

    public GyroscopeSensorFactory(final ASensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isGyroscopeDeactivated()) {
            throw new SensorNotActivatedException("The gyroscope is not activated!");
        } else {
            return getImplementation();
        }
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
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
            gyroscopeImplementation = sensorManager.getDefaultSensor(SENSOR_TYPE);
        }
    }
}