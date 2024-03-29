package michael.linker.gestureid.data.sensor.factory.composite;

import michael.linker.gestureid.config.sensor.SensorsConfiguration;
import michael.linker.gestureid.data.sensor.factory.ISensorFactory;
import michael.linker.gestureid.data.sensor.factory.SensorNotActivatedException;
import michael.linker.gestureid.data.sensor.factory.SensorNotFoundException;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.core.sensor.manager.AHardwareSensorManager;
import michael.linker.gestureid.core.sensor.sensor.SensorWrapper;

/**
 * Returns a Rotation Vector sensor implementation
 */
public class RotationVectorSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = CompositeSensorType.ROTATION_VECTOR;
    private final AHardwareSensorManager sensorManager;
    private SensorWrapper rotationVectorSensorImplementation;

    public RotationVectorSensorFactory(final AHardwareSensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsConfiguration.Build.isRotationVectorDeactivated()) {
            throw new SensorNotActivatedException("The rotation vector sensor is not activated!");
        } else {
            return this.getImplementation();
        }
    }

    @Override
    public SensorWrapper getImplementation() throws SensorNotFoundException {
        buildImplementation();
        if (rotationVectorSensorImplementation != null) {
            return rotationVectorSensorImplementation;
        } else {
            throw new SensorNotFoundException("An available rotation vector sensor was not found!");
        }
    }

    @Override
    public SensorType getSensorType() {
        return SENSOR_TYPE;
    }

    private void buildImplementation() {
        if (rotationVectorSensorImplementation == null) {
            rotationVectorSensorImplementation =
                    new SensorWrapper(SENSOR_TYPE, sensorManager.getDefaultSensor(SENSOR_TYPE));
        }
    }
}
