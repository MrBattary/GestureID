package michael.linker.gestureid.sensor.factory.base;

import michael.linker.gestureid.config.SensorsBuildConfiguration;
import michael.linker.gestureid.sensor.factory.ISensorFactory;
import michael.linker.gestureid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestureid.sensor.factory.SensorNotFoundException;
import michael.linker.gestureid.sensor.type.BaseSensorType;
import michael.linker.gestureid.sensor.type.SensorType;
import michael.linker.gestureid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestureid.sensor.wrapper.sensor.SensorWrapper;

/**
 * Returns a Magnetometer sensor implementation
 */
public class MagneticFieldSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = BaseSensorType.MAGNETOMETER;
    private final ASensorManager sensorManager;
    private SensorWrapper magnetometerImplementation;

    public MagneticFieldSensorFactory(final ASensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isMagnetometerDeactivated()) {
            throw new SensorNotActivatedException("The magnetic field sensor is not activated");
        } else {
            return this.getImplementation();
        }
    }

    @Override
    public SensorWrapper getImplementation() throws SensorNotFoundException {
        buildImplementation();
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

    private void buildImplementation() {
        if (magnetometerImplementation == null) {
            magnetometerImplementation =
                    new SensorWrapper(SENSOR_TYPE, sensorManager.getDefaultSensor(SENSOR_TYPE));
        }
    }
}
