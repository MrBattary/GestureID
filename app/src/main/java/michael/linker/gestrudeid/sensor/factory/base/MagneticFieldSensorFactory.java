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
 * Returns a Magnetometer sensor implementation
 */
public class MagneticFieldSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = BaseSensorType.MAGNETOMETER;
    private static Sensor magnetometerImplementation;

    public MagneticFieldSensorFactory(final ASensorManager sensorManager) {
        magnetometerImplementation
                = sensorManager.getDefaultSensor(SENSOR_TYPE);
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
        return SENSOR_TYPE;
    }
}
