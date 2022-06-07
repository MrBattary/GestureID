package michael.linker.gestrudeid.sensor.listener.provider;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.sensor.listener.base.AccelerometerSensorListener;
import michael.linker.gestrudeid.sensor.listener.base.GyroscopeSensorListener;
import michael.linker.gestrudeid.sensor.listener.base.MagneticFieldSensorListener;
import michael.linker.gestrudeid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.synchronizer.IEventSynchronizer;

public class SensorListenerProvider implements ISensorListenerProvider {
    private final Map<Integer, ISensorListener> sensorListeners = new HashMap<>();

    public SensorListenerProvider(final IEventSynchronizer eventSynchronizer,
            final ISensorListenerSuppressor listenerSuppressor) {
        initializeSensorListeners(eventSynchronizer, listenerSuppressor);
    }

    @Override
    public ISensorListener getListener(SensorType sensorType)
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
        // TODO: Add composite listeners
    }
}
