package michael.linker.gestureid.sensor.listener.provider;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestureid.config.sensor.SensorListenerConfiguration;
import michael.linker.gestureid.sensor.listener.ISensorListener;
import michael.linker.gestureid.sensor.listener.base.AccelerometerSensorListener;
import michael.linker.gestureid.sensor.listener.base.GyroscopeSensorListener;
import michael.linker.gestureid.sensor.listener.base.MagneticFieldSensorListener;
import michael.linker.gestureid.sensor.listener.composite.GeomagneticRotationVectorSensorListener;
import michael.linker.gestureid.sensor.listener.composite.GravitySensorListener;
import michael.linker.gestureid.sensor.listener.composite.LinearAccelerationSensorListener;
import michael.linker.gestureid.sensor.listener.composite.RotationVectorSensorListener;
import michael.linker.gestureid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

public class SensorListenerProvider implements ISensorListenerProvider {
    private final Map<Integer, ISensorListener> sensorListeners = new HashMap<>();

    public SensorListenerProvider() {
        initializeSensorListeners(SensorListenerConfiguration.getSensorListenerSuppressor());
    }

    @Override
    public ISensorListener getListener(final SensorType sensorType)
            throws SensorListenerProviderNotFoundException {
        ISensorListener sensorListener = sensorListeners.get(sensorType.toInt());
        if (sensorListener != null) {
            return sensorListener;
        } else {
            throw new SensorListenerProviderNotFoundException(
                    "Not found sensor listener for sensor with ID: "
                            + sensorType.toInt());
        }
    }

    private void initializeSensorListeners(
            final ISensorListenerSuppressor listenerSuppressor) {
        initializeBaseSensorListeners(listenerSuppressor);
        initializeCompositeSensorListeners(listenerSuppressor);
    }

    private void initializeBaseSensorListeners(final ISensorListenerSuppressor listenerSuppressor) {
        listenerSuppressor.registerListener(BaseSensorType.ACCELEROMETER);
        sensorListeners.put(BaseSensorType.ACCELEROMETER.toInt(),
                new AccelerometerSensorListener());

        listenerSuppressor.registerListener(BaseSensorType.GYROSCOPE);
        sensorListeners.put(BaseSensorType.GYROSCOPE.toInt(),
                new GyroscopeSensorListener());

        listenerSuppressor.registerListener(BaseSensorType.MAGNETOMETER);
        sensorListeners.put(BaseSensorType.MAGNETOMETER.toInt(),
                new MagneticFieldSensorListener());
    }

    private void initializeCompositeSensorListeners(
            final ISensorListenerSuppressor listenerSuppressor) {
        listenerSuppressor.registerListener(CompositeSensorType.GRAVITY);
        sensorListeners.put(CompositeSensorType.GRAVITY.toInt(),
                new GravitySensorListener());

        listenerSuppressor.registerListener(CompositeSensorType.LINEAR_ACCELERATION);
        sensorListeners.put(CompositeSensorType.LINEAR_ACCELERATION.toInt(),
                new LinearAccelerationSensorListener());

        listenerSuppressor.registerListener(CompositeSensorType.ROTATION_VECTOR);
        sensorListeners.put(CompositeSensorType.ROTATION_VECTOR.toInt(),
                new RotationVectorSensorListener());

        listenerSuppressor.registerListener(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR);
        sensorListeners.put(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR.toInt(),
                new GeomagneticRotationVectorSensorListener());
    }
}
