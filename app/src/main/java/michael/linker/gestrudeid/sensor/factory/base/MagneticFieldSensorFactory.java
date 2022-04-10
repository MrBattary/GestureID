package michael.linker.gestrudeid.sensor.factory.base;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.BaseSensorType;

/**
 * Returns a Magnetometer sensor implementation
 */
public class MagneticFieldSensorFactory implements ISensorFactory {
    private final Sensor magnetometerImplementation;

    public MagneticFieldSensorFactory(final SensorManager sensorManager) {
        magnetometerImplementation = sensorManager.getDefaultSensor(BaseSensorType.MAGNETOMETER);
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (magnetometerImplementation != null) {
            return magnetometerImplementation;
        } else {
            throw new SensorNotFoundException(
                    "An available linear acceleration sensor was not found!");
        }
    }
}
