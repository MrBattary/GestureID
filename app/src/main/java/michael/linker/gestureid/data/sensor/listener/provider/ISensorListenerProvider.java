package michael.linker.gestureid.data.sensor.listener.provider;

import michael.linker.gestureid.data.sensor.listener.ISensorListener;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

/**
 * Provider for listeners
 */
public interface ISensorListenerProvider {
    /**
     * Returns a sensor listener by sensor type
     *
     * @param sensorType [Base/Composite]SensorType
     * @return SensorListener
     * @throws SensorListenerProviderNotFoundException If required listener was not found
     */
    ISensorListener getListener(SensorType sensorType)
            throws SensorListenerProviderNotFoundException;
}
