package michael.linker.gestrudeid.sensor.factory.base;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.BaseSensorType;

/**
 * Returns an Accelerometer implementation
 */

public class AccelerometerSensorFactory implements ISensorFactory {
    private final Sensor accelerometerImplementation;

    public AccelerometerSensorFactory(final SensorManager sensorManager) {
        accelerometerImplementation = sensorManager.getDefaultSensor(BaseSensorType.ACCELEROMETER);
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (accelerometerImplementation != null) {
            return accelerometerImplementation;
        } else {
            throw new SensorNotFoundException("An available accelerometer was not found!");
        }
    }
}
