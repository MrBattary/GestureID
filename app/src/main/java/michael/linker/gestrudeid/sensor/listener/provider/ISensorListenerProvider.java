package michael.linker.gestrudeid.sensor.listener.provider;

import michael.linker.gestrudeid.sensor.listener.primitive.ISensorListener;
import michael.linker.gestrudeid.sensor.types.SensorType;

/**
 * Provider for listeners
 */
public interface ISensorListenerProvider {
    /**
     * Returns a sensor listener by sensor type
     *
     * @param sensorType [Base/Composite]SensorType
     * @return SensorListener
     */
    ISensorListener getListener(SensorType sensorType);
}
