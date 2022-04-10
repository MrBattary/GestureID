package michael.linker.gestrudeid.sensor.config;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.factory.DefaultSensorFactory;
import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.factory.base.AccelerometerSensorFactory;
import michael.linker.gestrudeid.sensor.factory.base.GyroscopeSensorFactory;
import michael.linker.gestrudeid.sensor.factory.base.MagneticFieldSensorFactory;
import michael.linker.gestrudeid.sensor.factory.composite.GeomagneticRotationVectorFactory;
import michael.linker.gestrudeid.sensor.factory.composite.GravitySensorFactory;
import michael.linker.gestrudeid.sensor.factory.composite.LinearAccelerationSensorFactory;
import michael.linker.gestrudeid.sensor.factory.composite.RotationVectorSensorFactory;
import michael.linker.gestrudeid.sensor.types.BaseSensorType;
import michael.linker.gestrudeid.sensor.types.CompositeSensorType;

public class SensorConfiguration implements ISensorConfiguration {
    private final static String TAG = SensorConfiguration.class.getCanonicalName();
    private final Map<Integer, ISensorFactory> sensorFactories = new HashMap<>();
    private final Map<Integer, Boolean> requiredSensors = new HashMap<>();
    private final ISensorFactory defaultFactory = new DefaultSensorFactory();

    public SensorConfiguration(final SensorManager sensorManager) {
        initializeSensorFactories(sensorManager);
        initializeRequiredSensors();
    }

    @Override
    public Sensor getSensor(Integer sensorType) throws SensorConfigurationNotFoundException {
        return null;
    }

    @Override
    public List<Sensor> getRequiredSensors() throws SensorConfigurationNotFoundException {
        try {
            List<Sensor> sensorList = new ArrayList<>();
            for (Map.Entry<Integer, Boolean> requiredSensor : requiredSensors.entrySet()) {
                if (requiredSensor.getValue()) {
                    ISensorFactory sensorFactory = sensorFactories.get(requiredSensor.getKey());
                    if (sensorFactory != null) {
                        sensorList.add(sensorFactory.getImplementation());
                    } else {
                        throw new SensorConfigurationNotFoundException(
                                "Not found sensor factory for sensor with ID: "
                                        + requiredSensor.getKey());
                    }
                }
            }
            return sensorList;
        } catch (SensorNotFoundException e) {
            Log.e(TAG, e.getMessage());
            throw new SensorConfigurationNotFoundException("Required sensor was not found!", e);
        }
    }

    @Override
    public List<Sensor> getAvailableSensors() {
        return null;
    }

    private void initializeSensorFactories(final SensorManager sensorManager) {
        initializeBaseSensorFactories(sensorManager);
        initializeCompositeSensorFactories(sensorManager);
    }

    private void initializeRequiredSensors() {
        initializeRequiredBaseSensors();
        initializeRequiredCompositeSensors();

    }

    private void initializeBaseSensorFactories(final SensorManager sensorManager) {
        sensorFactories.put(BaseSensorType.ACCELEROMETER,
                new AccelerometerSensorFactory(sensorManager));
        sensorFactories.put(BaseSensorType.GYROSCOPE, new GyroscopeSensorFactory(sensorManager));
        sensorFactories.put(BaseSensorType.MAGNETOMETER,
                new MagneticFieldSensorFactory(sensorManager));
    }

    private void initializeCompositeSensorFactories(final SensorManager sensorManager) {
        sensorFactories.put(CompositeSensorType.GRAVITY, new GravitySensorFactory(sensorManager));
        sensorFactories.put(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR,
                new GeomagneticRotationVectorFactory(sensorManager));
        sensorFactories.put(CompositeSensorType.LINEAR_ACCELERATION,
                new LinearAccelerationSensorFactory(sensorManager));
        sensorFactories.put(CompositeSensorType.ROTATION_VECTOR,
                new RotationVectorSensorFactory(sensorManager));
    }

    private void initializeRequiredBaseSensors() {
        requiredSensors.put(BaseSensorType.ACCELEROMETER,
                SensorsBuildConfiguration.isAccelerometerRequired());
        requiredSensors.put(BaseSensorType.GYROSCOPE,
                SensorsBuildConfiguration.isGyroscopeRequired());
        requiredSensors.put(BaseSensorType.MAGNETOMETER,
                SensorsBuildConfiguration.isMagnetometerRequired());
    }

    private void initializeRequiredCompositeSensors() {
        requiredSensors.put(CompositeSensorType.GRAVITY,
                SensorsBuildConfiguration.isGravityRequired());
        requiredSensors.put(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR,
                SensorsBuildConfiguration.isGeomagneticRotationVectorRequired());
        requiredSensors.put(CompositeSensorType.LINEAR_ACCELERATION,
                SensorsBuildConfiguration.isLinearAccelerationRequired());
        requiredSensors.put(CompositeSensorType.ROTATION_VECTOR,
                SensorsBuildConfiguration.isRotationVectorRequired());
    }
}
