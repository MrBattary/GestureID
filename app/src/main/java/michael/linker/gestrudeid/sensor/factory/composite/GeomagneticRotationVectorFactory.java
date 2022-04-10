package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.CompositeSensorType;

/**
 * Returns a Geomagnetic Rotation Vector sensor implementation
 */
public class GeomagneticRotationVectorFactory implements ISensorFactory {
    private final Sensor geomagneticRotationVectorSensorImplementation;

    public GeomagneticRotationVectorFactory(final SensorManager sensorManager) {
        geomagneticRotationVectorSensorImplementation = sensorManager.getDefaultSensor(
                CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR);
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
