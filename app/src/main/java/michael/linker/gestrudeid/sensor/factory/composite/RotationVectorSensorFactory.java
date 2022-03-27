package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.tag.category.Attitude;
import michael.linker.gestrudeid.sensor.tag.type.Composite;

/**
 * Returns a Rotation Vector sensor implementation
 */
public class RotationVectorSensorFactory implements ISensorFactory {
    @Composite(required = {"Accelerometer", "Magnetometer", "Gyroscope"})
    @Attitude
    private final Sensor rotationVectorSensorImplementation;

    public RotationVectorSensorFactory(final SensorManager sensorManager) {
        rotationVectorSensorImplementation = sensorManager.getDefaultSensor(
                Sensor.TYPE_MAGNETIC_FIELD);
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
