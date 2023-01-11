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
 * Returns a Geomagnetic Rotation Vector sensor implementation
 */
public class GeomagneticRotationVectorSensorFactory implements ISensorFactory {
    private final static SensorType SENSOR_TYPE = CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR;
    private final AHardwareSensorManager sensorManager;
    private SensorWrapper geomagneticRotationVectorSensorImplementation;

    public GeomagneticRotationVectorSensorFactory(final AHardwareSensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    @Override
    public SensorWrapper getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsConfiguration.Build.isGeomagneticRotationVectorDeactivated()) {
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
