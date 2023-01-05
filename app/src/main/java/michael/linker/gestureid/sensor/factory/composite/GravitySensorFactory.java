package michael.linker.gestureid.sensor.factory.composite;

import michael.linker.gestureid.config.sensor.SensorsConfiguration;
import michael.linker.gestureid.sensor.factory.ISensorFactory;
import michael.linker.gestureid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestureid.sensor.factory.SensorNotFoundException;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.core.sensor.manager.AHardwareSensorManager;
import michael.linker.gestureid.core.sensor.sensor.SensorWrapper;

/**
 * Returns a Gravity sensor implementation
 */
public class GravitySensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = CompositeSensorType.GRAVITY;
    private final AHardwareSensorManager sensorManager;
    private SensorWrapper gravityImplementation;

    public GravitySensorFactory(final AHardwareSensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsConfiguration.Build.isGravityDeactivated()) {
            throw new SensorNotActivatedException("The gravity sensor is not activated!");
        } else {
            return this.getImplementation();
        }
    }

    @Override
    public SensorWrapper getImplementation() throws SensorNotFoundException {
        buildImplementation();
        if (gravityImplementation != null) {
            return gravityImplementation;
        } else {
            throw new SensorNotFoundException("An available gravity sensor was not found!");
        }
    }

    @Override
    public SensorType getSensorType() {
        return SENSOR_TYPE;
    }

    private void buildImplementation() {
        if (gravityImplementation == null) {
            gravityImplementation =
                    new SensorWrapper(SENSOR_TYPE, sensorManager.getDefaultSensor(SENSOR_TYPE));
        }
    }
}