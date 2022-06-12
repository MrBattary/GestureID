package michael.linker.gestrudeid.sensor.provider;

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
import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.wrapper.sensor.SensorWrapper;

public class SensorProvider implements ISensorProvider {
    private final static String TAG = SensorProvider.class.getCanonicalName();
    private final Map<Integer, ISensorFactory> sensorFactories = new HashMap<>();

    public SensorProvider(final ASensorManager sensorManager) {
        initializeSensorFactories(sensorManager);
    }

    @Override
    public SensorWrapper getSensor(final SensorType sensorType)
            throws SensorProviderNotFoundException {
        try {
            ISensorFactory sensorFactory = sensorFactories.get(sensorType.toInt());
            if (sensorFactory != null) {
                return sensorFactory.getImplementation();
            } else {
                throw new SensorProviderNotFoundException(
                        "Not found sensor factory for sensor with ID: "
                                + sensorType.toInt());
            }
        } catch (SensorNotFoundException e) {
            Log.e(TAG, e.getMessage());
            throw new SensorProviderNotFoundException("Required sensor was not found!", e);
        }
    }

    @Override
    public List<SensorWrapper> getActivatedSensors() throws SensorProviderNotFoundException {
        try {
            List<SensorWrapper> sensorList = new ArrayList<>();
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
            throw new SensorProviderNotFoundException("Required sensor was not found!", e);
        }
    }

    @Override
    public List<SensorWrapper> getSensors() {
        List<SensorWrapper> sensorList = new ArrayList<>();
        for (ISensorFactory sensorFactory : sensorFactories.values()) {
            try {
                sensorList.add(sensorFactory.getImplementation());
            } catch (SensorNotFoundException e) {
                Log.w(TAG, e.getMessage());
            }
        }
        return sensorList;
    }

    private void initializeSensorFactories(final ASensorManager sensorManager) {
        initializeBaseSensorFactories(sensorManager);
        initializeCompositeSensorFactories(sensorManager);
    }

    private void initializeBaseSensorFactories(final ASensorManager sensorManager) {
        sensorFactories.put(BaseSensorType.ACCELEROMETER.toInt(),
                new AccelerometerSensorFactory(sensorManager));
        sensorFactories.put(BaseSensorType.GYROSCOPE.toInt(),
                new GyroscopeSensorFactory(sensorManager));
        sensorFactories.put(BaseSensorType.MAGNETOMETER.toInt(),
                new MagneticFieldSensorFactory(sensorManager));
    }

    private void initializeCompositeSensorFactories(final ASensorManager sensorManager) {
        sensorFactories.put(CompositeSensorType.GRAVITY.toInt(),
                new GravitySensorFactory(sensorManager));
        sensorFactories.put(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR.toInt(),
                new GeomagneticRotationVectorFactory(sensorManager));
        sensorFactories.put(CompositeSensorType.LINEAR_ACCELERATION.toInt(),
                new LinearAccelerationSensorFactory(sensorManager));
        sensorFactories.put(CompositeSensorType.ROTATION_VECTOR.toInt(),
                new RotationVectorSensorFactory(sensorManager));
    }
}
