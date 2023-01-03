package michael.linker.gestureid.sensor.listener.suppressor;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

public class SensorListenerSuppressor implements ISensorListenerSuppressor {
    private boolean suppressAllListeners = true;
    private final Map<SensorType, Boolean> listeners = new HashMap<>();

    @Override
    public void suppressAllListeners() {
        suppressAllListeners = true;
    }

    @Override
    public void unsuppressAllListeners() {
        suppressAllListeners = false;
    }

    @Override
    public boolean isAllListenersSuppressed() {
        return suppressAllListeners;
    }

    @Override
    public Boolean isListenerSuppressed(SensorType sensorType)
            throws SensorListenerSuppressorNotFoundException {
        final Boolean status = listeners.get(sensorType);
        if (status == null) {
            throw new SensorListenerSuppressorNotFoundException(
                    "Unable to get the suppression status, the " + sensorType.toString()
                            + " listener has not yet been registered!"
            );
        }
        return status;
    }

    @Override
    public void suppressListener(SensorType sensorType)
            throws SensorListenerSuppressorNotFoundException {
        setListenerSuppressionStatus(sensorType, true);
    }

    @Override
    public void unsuppressListener(SensorType sensorType)
            throws SensorListenerSuppressorNotFoundException {
        setListenerSuppressionStatus(sensorType, false);
    }

    @Override
    public Map<SensorType, Boolean> getListenersSuppressedStatus() {
        return listeners;
    }

    @Override
    public void registerListener(SensorType sensorType) {
        listeners.put(sensorType, true);
    }

    private void setListenerSuppressionStatus(SensorType sensorType, boolean suppressionStatus)
            throws SensorListenerSuppressorNotFoundException {
        if (listeners.containsKey(sensorType)) {
            listeners.replace(sensorType, suppressionStatus);
        } else {
            throw new SensorListenerSuppressorNotFoundException(
                    "Unable to set the suppression status, the " + sensorType.toString()
                            + " listener has not yet been registered!"
            );
        }
    }
}
