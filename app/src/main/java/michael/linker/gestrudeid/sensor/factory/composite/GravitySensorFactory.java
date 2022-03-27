package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.tag.category.Attitude;
import michael.linker.gestrudeid.sensor.tag.type.Composite;

/**
 * Returns a Gravity sensor implementation
 */
public class GravitySensorFactory implements ISensorFactory {
    @Composite(
            required = "Accelerometer",
            replaceable = "Gyroscope",
            alternative = "Magnetometer"
    )
    @Attitude
    private final Sensor gravityImplementation;

    public GravitySensorFactory(final SensorManager sensorManager) {
        gravityImplementation = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (gravityImplementation != null) {
            return gravityImplementation;
        } else {
            throw new SensorNotFoundException("An available gravity sensor was not found!");
        }
    }
}