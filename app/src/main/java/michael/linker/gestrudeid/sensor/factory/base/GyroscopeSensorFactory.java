package michael.linker.gestrudeid.sensor.factory.base;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.tag.type.Base;

/**
 * Returns a Gyroscope implementation
 */
public class GyroscopeSensorFactory implements ISensorFactory {
    @Base
    private final Sensor gyroscopeImplementation;

    public GyroscopeSensorFactory(final SensorManager sensorManager) {
        gyroscopeImplementation = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (gyroscopeImplementation != null) {
            return gyroscopeImplementation;
        } else {
            throw new SensorNotFoundException("An available gyroscope was not found!");
        }
    }
}