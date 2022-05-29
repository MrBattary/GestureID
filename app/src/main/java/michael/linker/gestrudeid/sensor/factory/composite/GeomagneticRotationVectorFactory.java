package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;

/**
 * Returns a Geomagnetic Rotation Vector sensor implementation
 */
public class GeomagneticRotationVectorFactory implements ISensorFactory {
    private static Sensor geomagneticRotationVectorSensorImplementation;

    public GeomagneticRotationVectorFactory(final SensorManager sensorManager) {
        geomagneticRotationVectorSensorImplementation = sensorManager.getDefaultSensor(
                CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR.toInt());
    }

    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isGeomagneticRotationVectorActivated()) {
            return this.getImplementation();
        } else {
            throw new SensorNotActivatedException(
                    "The geomagnetic rotation vector sensor is not activated!");
        }
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

    @Override
    public SensorType getSensorType() {
        return CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR;
    }
}
