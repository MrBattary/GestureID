package michael.linker.gestrudeid.sensor.factory.base;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.tag.type.Base;

/**
 * Returns an Accelerometer implementation
 */

public class AccelerometerSensorFactory implements ISensorFactory {
    @Base
    private final Sensor accelerometerImplementation;

    public AccelerometerSensorFactory(final SensorManager sensorManager) {
        accelerometerImplementation = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
