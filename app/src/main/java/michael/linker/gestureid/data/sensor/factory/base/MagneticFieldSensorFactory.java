package michael.linker.gestureid.data.sensor.factory.base;

import michael.linker.gestureid.config.sensor.SensorsConfiguration;
import michael.linker.gestureid.data.sensor.factory.ISensorFactory;
import michael.linker.gestureid.data.sensor.factory.SensorNotActivatedException;
import michael.linker.gestureid.data.sensor.factory.SensorNotFoundException;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.core.sensor.manager.AHardwareSensorManager;
import michael.linker.gestureid.core.sensor.sensor.SensorWrapper;

/**
 * Returns a Magnetometer sensor implementation
 */
public class MagneticFieldSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = BaseSensorType.MAGNETOMETER;
    private final AHardwareSensorManager sensorManager;
    private SensorWrapper magnetometerImplementation;

    public MagneticFieldSensorFactory(final AHardwareSensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsConfiguration.Build.isMagnetometerDeactivated()) {
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
