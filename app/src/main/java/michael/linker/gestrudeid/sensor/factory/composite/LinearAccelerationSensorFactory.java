package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.tag.category.Activity;
import michael.linker.gestrudeid.sensor.tag.type.Composite;

/**
 * Returns a Linear Acceleration sensor implementation
 */
public class LinearAccelerationSensorFactory implements ISensorFactory {
    @Composite(
            required = "Accelerometer",
            replaceable = "Gyroscope",
            alternative = "Magnetometer"
    )
    @Activity
    private static Sensor linearAccelerationImplementation;

    public LinearAccelerationSensorFactory(final SensorManager sensorManager) {
        linearAccelerationImplementation = sensorManager.getDefaultSensor(
                Sensor.TYPE_LINEAR_ACCELERATION);
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (linearAccelerationImplementation != null) {
            return linearAccelerationImplementation;
        } else {
            throw new SensorNotFoundException(
                    "An available linear acceleration sensor was not found!");
        }
    }
}
