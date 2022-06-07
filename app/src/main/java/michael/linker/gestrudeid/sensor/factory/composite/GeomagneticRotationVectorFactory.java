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
    private final ASensorManager sensorManager;

    public GeomagneticRotationVectorFactory(final ASensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isGeomagneticRotationVectorDeactivated()) {
            throw new SensorNotActivatedException(
                    "The geomagnetic rotation vector sensor is not activated!");
        } else {
            return this.getImplementation();
        }
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        buildImplementation();
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

    private void buildImplementation() {
        if (geomagneticRotationVectorSensorImplementation == null) {
            geomagneticRotationVectorSensorImplementation
                    = sensorManager.getDefaultSensor(SENSOR_TYPE);
        }
    }
}
