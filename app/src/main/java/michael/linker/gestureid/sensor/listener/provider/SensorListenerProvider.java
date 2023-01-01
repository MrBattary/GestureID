package michael.linker.gestureid.sensor.listener.provider;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestureid.sensor.listener.ISensorListener;
import michael.linker.gestureid.sensor.listener.base.AccelerometerSensorListener;
import michael.linker.gestureid.sensor.listener.base.GyroscopeSensorListener;
import michael.linker.gestureid.sensor.listener.base.MagneticFieldSensorListener;
import michael.linker.gestureid.sensor.listener.composite.GeomagneticRotationVectorSensorListener;
import michael.linker.gestureid.sensor.listener.composite.GravitySensorListener;
import michael.linker.gestureid.sensor.listener.composite.LinearAccelerationSensorListener;
import michael.linker.gestureid.sensor.listener.composite.RotationVectorSensorListener;
import michael.linker.gestureid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.sensor.type.BaseSensorType;
import michael.linker.gestureid.sensor.type.CompositeSensorType;
import michael.linker.gestureid.sensor.type.SensorType;
import michael.linker.gestureid.event.synchronizer.IEventSynchronizer;

public class SensorListenerProvider implements ISensorListenerProvider {
    private final Map<Integer, ISensorListener> sensorListeners = new HashMap<>();

    public SensorListenerProvider(final IEventSynchronizer eventSynchronizer,
            final ISensorListenerSuppressor listenerSuppressor) {
        initializeSensorListeners(eventSynchronizer, listenerSuppressor);
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

    private void initializeSensorListeners(final IEventSynchronizer eventSynchronizer,
            final ISensorListenerSuppressor listenerSuppressor) {
        initializeBaseSensorListeners(eventSynchronizer, listenerSuppressor);
        initializeCompositeSensorListeners(eventSynchronizer, listenerSuppressor);
    }

    private void initializeBaseSensorListeners(final IEventSynchronizer eventSynchronizer,
            final ISensorListenerSuppressor listenerSuppressor) {
        listenerSuppressor.registerListener(BaseSensorType.ACCELEROMETER);
        sensorListeners.put(BaseSensorType.ACCELEROMETER.toInt(),
                new AccelerometerSensorListener(eventSynchronizer, listenerSuppressor));
        listenerSuppressor.registerListener(BaseSensorType.GYROSCOPE);
        sensorListeners.put(BaseSensorType.GYROSCOPE.toInt(),
                new GyroscopeSensorListener(eventSynchronizer, listenerSuppressor));
        listenerSuppressor.registerListener(BaseSensorType.MAGNETOMETER);
        sensorListeners.put(BaseSensorType.MAGNETOMETER.toInt(),
                new MagneticFieldSensorListener(eventSynchronizer, listenerSuppressor));
    }

    private void initializeCompositeSensorListeners(final IEventSynchronizer eventSynchronizer,
            final ISensorListenerSuppressor listenerSuppressor) {
        listenerSuppressor.registerListener(CompositeSensorType.GRAVITY);
        sensorListeners.put(CompositeSensorType.GRAVITY.toInt(),
                new GravitySensorListener(eventSynchronizer, listenerSuppressor));
        listenerSuppressor.registerListener(CompositeSensorType.LINEAR_ACCELERATION);
        sensorListeners.put(CompositeSensorType.LINEAR_ACCELERATION.toInt(),
                new LinearAccelerationSensorListener(eventSynchronizer, listenerSuppressor));
        listenerSuppressor.registerListener(CompositeSensorType.ROTATION_VECTOR);
        sensorListeners.put(CompositeSensorType.ROTATION_VECTOR.toInt(),
                new RotationVectorSensorListener(eventSynchronizer, listenerSuppressor));
        listenerSuppressor.registerListener(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR);
        sensorListeners.put(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR.toInt(),
                new GeomagneticRotationVectorSensorListener(eventSynchronizer, listenerSuppressor));
    }
}
