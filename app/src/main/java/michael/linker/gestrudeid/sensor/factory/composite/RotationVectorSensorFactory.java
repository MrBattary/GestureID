package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.CompositeSensor;

/**
 * Returns a Rotation Vector sensor implementation
 */
public class RotationVectorSensorFactory implements ISensorFactory {
    private final Sensor rotationVectorSensorImplementation;

    public RotationVectorSensorFactory(final SensorManager sensorManager) {
        rotationVectorSensorImplementation = sensorManager.getDefaultSensor(
                CompositeSensor.ROTATION_VECTOR);
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (rotationVectorSensorImplementation != null) {
            return rotationVectorSensorImplementation;
        } else {
            throw new SensorNotFoundException("An available rotation vector sensor was not found!");
        }
    }
}
