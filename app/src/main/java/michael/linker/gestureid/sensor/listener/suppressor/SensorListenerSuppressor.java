package michael.linker.gestureid.sensor.listener.suppressor;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

public class SensorListenerSuppressor implements ISensorListenerSuppressor {
    private final Map<SensorType, Boolean> listenerSuppressedMap = new HashMap<>();

    @Override
    public void suppressAllListeners() {
        for (SensorType sensorType : listenerSuppressedMap.keySet()) {
            suppressListener(sensorType);
        }
    }

    @Override
    public void unsuppressAllListeners() {
        for (SensorType sensorType : listenerSuppressedMap.keySet()) {
            unsuppressListener(sensorType);
        }
    }

    @Override
    public boolean isAllListenersSuppressed() {
        boolean isAllListenersSuppressed = true;
        for (Boolean listenerSuppression : listenerSuppressedMap.values()) {
            if (!listenerSuppression) {
                isAllListenersSuppressed = false;
                break;
            }
        }
        return isAllListenersSuppressed;
    }

    @Override
    public Boolean isListenerSuppressed(SensorType sensorType)
            throws SensorListenerSuppressorNotFoundException {
        final Boolean status = listenerSuppressedMap.get(sensorType);
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
        return listenerSuppressedMap;
    }

    @Override
    public void registerListener(SensorType sensorType) {
        listenerSuppressedMap.put(sensorType, true);
    }

    private void setListenerSuppressionStatus(SensorType sensorType, boolean suppressionStatus)
            throws SensorListenerSuppressorNotFoundException {
        if (listenerSuppressedMap.containsKey(sensorType)) {
            listenerSuppressedMap.replace(sensorType, suppressionStatus);
        } else {
            throw new SensorListenerSuppressorNotFoundException(
                    "Unable to set the suppression status, the " + sensorType.toString()
                            + " listener has not yet been registered!"
            );
        }
    }
}
