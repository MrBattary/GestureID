package michael.linker.gestrudeid.sensor.core.provider;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import michael.linker.gestrudeid.sensor.core.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.core.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.core.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.core.factory.base.AccelerometerSensorFactory;
import michael.linker.gestrudeid.sensor.core.factory.base.GyroscopeSensorFactory;
import michael.linker.gestrudeid.sensor.core.factory.base.MagneticFieldSensorFactory;
import michael.linker.gestrudeid.sensor.core.factory.composite.GeomagneticRotationVectorFactory;
import michael.linker.gestrudeid.sensor.core.factory.composite.GravitySensorFactory;
import michael.linker.gestrudeid.sensor.core.factory.composite.LinearAccelerationSensorFactory;
import michael.linker.gestrudeid.sensor.core.factory.composite.RotationVectorSensorFactory;
import michael.linker.gestrudeid.sensor.types.BaseSensorType;
import michael.linker.gestrudeid.sensor.types.CompositeSensorType;
import michael.linker.gestrudeid.sensor.types.SensorType;

public class SensorProvider implements ISensorProvider {
    private final static String TAG = SensorProvider.class.getCanonicalName();
    private final Map<Integer, ISensorFactory> sensorFactories = new HashMap<>();

    public SensorProvider(final SensorManager sensorManager) {
        initializeSensorFactories(sensorManager);
    }

    @Override
    public Sensor getSensor(final SensorType sensorType) throws SensorProviderNotFoundException {
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
    public List<Sensor> getActivatedSensors() throws SensorProviderNotFoundException {
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
            throw new SensorProviderNotFoundException("Required sensor was not found!", e);
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
        sensorFactories.put(BaseSensorType.ACCELEROMETER.toInt(),
                new AccelerometerSensorFactory(sensorManager));
        sensorFactories.put(BaseSensorType.GYROSCOPE.toInt(),
                new GyroscopeSensorFactory(sensorManager));
        sensorFactories.put(BaseSensorType.MAGNETOMETER.toInt(),
                new MagneticFieldSensorFactory(sensorManager));
    }

    private void initializeCompositeSensorFactories(final SensorManager sensorManager) {
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
