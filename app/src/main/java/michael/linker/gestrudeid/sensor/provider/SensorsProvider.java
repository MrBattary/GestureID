package michael.linker.gestrudeid.sensor.provider;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotActivatedException;
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

public class SensorsProvider implements ISensorsProvider {
    private final static String TAG = SensorsProvider.class.getCanonicalName();
    private final Map<Integer, ISensorFactory> sensorFactories = new HashMap<>();

    public SensorsProvider(final SensorManager sensorManager) {
        initializeSensorFactories(sensorManager);
    }

    @Override
    public Sensor getSensor(final Integer sensorType) throws SensorsProviderNotFoundException {
        try {
            ISensorFactory sensorFactory = sensorFactories.get(sensorType);
            if (sensorFactory != null) {
                return sensorFactory.getImplementation();
            } else {
                throw new SensorsProviderNotFoundException(
                        "Not found sensor factory for sensor with ID: "
                                + sensorType);
            }
        } catch (SensorNotFoundException e) {
            Log.e(TAG, e.getMessage());
            throw new SensorsProviderNotFoundException("Required sensor was not found!", e);
        }
    }

    @Override
    public List<Sensor> getActivatedSensors() throws SensorsProviderNotFoundException {
        try {
            List<Sensor> sensorList = new ArrayList<>();
            for (ISensorFactory sensorFactory : sensorFactories.values()) {
                try {
                    sensorList.add(sensorFactory.getActivatedImplementation());
                } catch (SensorNotActivatedException e) {
                    Log.w(TAG, e.getMessage());
                }
            }
            return sensorList;
        } catch (SensorNotFoundException e) {
            Log.e(TAG, e.getMessage());
            throw new SensorsProviderNotFoundException("Required sensor was not found!", e);
        }
    }

    @Override
    public List<Sensor> getSensors() {
        List<Sensor> sensorList = new ArrayList<>();
        for (ISensorFactory sensorFactory : sensorFactories.values()) {
            try {
                sensorList.add(sensorFactory.getImplementation());
            } catch (SensorNotFoundException e) {
                Log.w(TAG, e.getMessage());
            }
        }
        return sensorList;
    }

    private void initializeSensorFactories(final SensorManager sensorManager) {
        initializeBaseSensorFactories(sensorManager);
        initializeCompositeSensorFactories(sensorManager);
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
}
