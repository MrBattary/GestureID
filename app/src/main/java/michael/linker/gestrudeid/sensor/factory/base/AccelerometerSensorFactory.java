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
 * Returns an Accelerometer implementation
 */
public class AccelerometerSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = BaseSensorType.ACCELEROMETER;
    private static Sensor accelerometerImplementation;
    private final ASensorManager sensorManager;

    public AccelerometerSensorFactory(final ASensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isAccelerometerDeactivated()) {
            throw new SensorNotActivatedException("The accelerometer is not activated!");
        } else {
            return getImplementation();
        }
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
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
            accelerometerImplementation = sensorManager.getDefaultSensor(SENSOR_TYPE);
        }
    }
}
