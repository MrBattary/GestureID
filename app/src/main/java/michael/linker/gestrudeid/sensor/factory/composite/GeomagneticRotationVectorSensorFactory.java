package michael.linker.gestrudeid.sensor.factory.composite;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.wrapper.sensor.SensorWrapper;

/**
 * Returns a Geomagnetic Rotation Vector sensor implementation
 */
public class GeomagneticRotationVectorSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR;
    private final ASensorManager sensorManager;
    private SensorWrapper geomagneticRotationVectorSensorImplementation;

    public GeomagneticRotationVectorSensorFactory(final ASensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isGeomagneticRotationVectorDeactivated()) {
            throw new SensorNotActivatedException(
                    "The geomagnetic rotation vector sensor is not activated!");
        } else {
            return this.getImplementation();
        }
    }

    @Override
    public SensorWrapper getImplementation() throws SensorNotFoundException {
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
                    = new SensorWrapper(SENSOR_TYPE, sensorManager.getDefaultSensor(SENSOR_TYPE));
        }
    }
}
