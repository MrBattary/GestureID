package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.CompositeSensor;

/**
 * Returns a Linear Acceleration sensor implementation
 */
public class LinearAccelerationSensorFactory implements ISensorFactory {
    private static Sensor linearAccelerationImplementation;

    public LinearAccelerationSensorFactory(final SensorManager sensorManager) {
        linearAccelerationImplementation = sensorManager.getDefaultSensor(
                CompositeSensor.LINEAR_ACCELERATION);
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
