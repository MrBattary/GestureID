package michael.linker.gestrudeid.sensor.core.factory.base;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.core.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.core.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.core.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.BaseSensorType;
import michael.linker.gestrudeid.sensor.types.SensorType;

/**
 * Returns a Magnetometer sensor implementation
 */
public class MagneticFieldSensorFactory implements ISensorFactory {
    private static Sensor magnetometerImplementation;

    public MagneticFieldSensorFactory(final SensorManager sensorManager) {
        magnetometerImplementation
                = sensorManager.getDefaultSensor(BaseSensorType.MAGNETOMETER.toInt());
    }

    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isMagnetometerActivated()) {
            return this.getImplementation();
        } else {
            throw new SensorNotActivatedException("The magnetic field sensor is not activated");
        }
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (magnetometerImplementation != null) {
            return magnetometerImplementation;
        } else {
            throw new SensorNotFoundException(
                    "An available magnetic field sensor was not found!");
        }
    }

    @Override
    public SensorType getSensorType() {
        return BaseSensorType.MAGNETOMETER;
    }
}
