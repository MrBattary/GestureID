package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;

/**
 * Returns a Geomagnetic Rotation Vector sensor implementation
 */
public class GeomagneticRotationVectorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR;
    private static Sensor geomagneticRotationVectorSensorImplementation;

    public GeomagneticRotationVectorFactory(final ASensorManager sensorManager) {
        geomagneticRotationVectorSensorImplementation = sensorManager.getDefaultSensor(SENSOR_TYPE);
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
        return SENSOR_TYPE;
    }
}
