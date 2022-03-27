package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.tag.category.Attitude;
import michael.linker.gestrudeid.sensor.tag.type.Composite;

/**
 * Returns a Geomagnetic Rotation Vector sensor implementation
 */
public class GeomagneticRotationVectorFactory implements ISensorFactory {
    @Composite(required = {"Accelerometer", "Magnetometer"})
    @Attitude
    private final Sensor geomagneticRotationVectorSensorImplementation;

    public GeomagneticRotationVectorFactory(final SensorManager sensorManager) {
        geomagneticRotationVectorSensorImplementation = sensorManager.getDefaultSensor(
                Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (geomagneticRotationVectorSensorImplementation != null) {
            return geomagneticRotationVectorSensorImplementation;
        } else {
            throw new SensorNotFoundException(
                    "An available geomagnetic rotation vector sensor was not found!");
        }
    }
}
