package michael.linker.gestrudeid.sensor.listener.provider;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.sensor.listener.base.AccelerometerSensorListener;
import michael.linker.gestrudeid.sensor.listener.base.GyroscopeSensorListener;
import michael.linker.gestrudeid.sensor.listener.base.MagneticFieldSensorListener;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.synchronizer.IEventSynchronizer;

public class SensorListenerProvider implements ISensorListenerProvider {
    private static final Map<Integer, ISensorListener> SENSOR_LISTENERS = new HashMap<>();

    public SensorListenerProvider(final IEventSynchronizer eventSynchronizer) {
        initializeSensorListeners(eventSynchronizer);
    }

    @Override
    public ISensorListener getListener(SensorType sensorType)
            throws SensorListenerProviderNotFoundException {
        ISensorListener sensorListener = SENSOR_LISTENERS.get(sensorType.toInt());
        if (sensorListener != null) {
            return sensorListener;
        } else {
            throw new SensorListenerProviderNotFoundException(
                    "Not found sensor listener for sensor with ID: "
                            + sensorType.toInt());
        }
    }

    private void initializeSensorListeners(final IEventSynchronizer  eventSynchronizer) {
        initializeBaseSensorListeners(eventSynchronizer);
        initializeCompositeSensorListeners(eventSynchronizer);
    }

    private void initializeBaseSensorListeners(final IEventSynchronizer eventSynchronizer) {
        SENSOR_LISTENERS.put(BaseSensorType.ACCELEROMETER.toInt(),
                new AccelerometerSensorListener(eventSynchronizer));
        SENSOR_LISTENERS.put(BaseSensorType.GYROSCOPE.toInt(),
                new GyroscopeSensorListener(eventSynchronizer));
        SENSOR_LISTENERS.put(BaseSensorType.MAGNETOMETER.toInt(),
                new MagneticFieldSensorListener(eventSynchronizer));
    }

    private void initializeCompositeSensorListeners(final IEventSynchronizer  eventSynchronizer) {
        // TODO: Add composite listeners
    }
}
