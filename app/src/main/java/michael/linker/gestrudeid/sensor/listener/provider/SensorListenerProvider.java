package michael.linker.gestrudeid.sensor.listener.provider;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.sensor.listener.base.AccelerometerSensorListener;
import michael.linker.gestrudeid.sensor.listener.base.GyroscopeSensorListener;
import michael.linker.gestrudeid.sensor.listener.base.MagneticFieldSensorListener;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;

public class SensorListenerProvider implements ISensorListenerProvider {
    private final Map<Integer, ISensorListener> sensorListeners = new HashMap<>();

    public SensorListenerProvider(IOutputStream outputStream) {
        initializeSensorListeners(outputStream);
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

    private void initializeSensorListeners(IOutputStream outputStream) {
        initializeBaseSensorListeners(outputStream);
        initializeCompositeSensorListeners(outputStream);
    }

    private void initializeBaseSensorListeners(IOutputStream outputStream) {
        sensorListeners.put(BaseSensorType.ACCELEROMETER.toInt(),
                new AccelerometerSensorListener(outputStream));
        sensorListeners.put(BaseSensorType.GYROSCOPE.toInt(),
                new GyroscopeSensorListener(outputStream));
        sensorListeners.put(BaseSensorType.MAGNETOMETER.toInt(),
                new MagneticFieldSensorListener(outputStream));
    }

    private void initializeCompositeSensorListeners(IOutputStream outputStream) {
        // TODO: Add composite listeners
    }
}
