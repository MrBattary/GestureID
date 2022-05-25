package michael.linker.gestrudeid.sensor.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import michael.linker.gestrudeid.sensor.formatter.ISensorModelFormatter;
import michael.linker.gestrudeid.sensor.model.ASensorModel;
import michael.linker.gestrudeid.sensor.types.SensorType;

public class SensorEventSynchronizer implements ISensorEventSynchronizer {
    private final ISensorModelFormatter sensorFormatter;

    private final Map<Integer, ASensorModel> registeredModels = new TreeMap<>();
    private final Set<Integer> attachedListeners = new TreeSet<>();

    public SensorEventSynchronizer(ISensorModelFormatter sensorFormatter) {
        this.sensorFormatter = sensorFormatter;
    }

    @Override
    public void registerEvent(ASensorModel sensorModel) throws
            SensorEventSynchronizerNotFoundException, SensorEventSynchronizerFailedException {
        final int sensorIdFromModel = sensorModel.getSensorType().toInt();
        tryToSynchronize();

        if (attachedListeners.contains(sensorIdFromModel)) {
            if (!registeredModels.containsKey(sensorIdFromModel)) {
                registeredModels.put(sensorIdFromModel, sensorModel);
            } else {
                throw new SensorEventSynchronizerFailedException(
                        "Event for the listener of the sensor with ID " + sensorIdFromModel +
                                " has already been registered!");
            }
        } else {
            throw new SensorEventSynchronizerNotFoundException("A listener of the sensor with ID "
                    + sensorIdFromModel + " has not been attached!");
        }
    }

    /**
     * Syncs if each listener has submitted their own model
     */
    private void tryToSynchronize() {
        if (registeredModels.keySet().equals(attachedListeners)) {
            sensorFormatter.format(new ArrayList<>(registeredModels.values()));
            registeredModels.clear();
        }
    }

    @Override
    public void attachOneListener(SensorType sensorType)
            throws SensorEventSynchronizerFailedException {
        if (!attachedListeners.contains(sensorType.toInt())) {
            attachedListeners.add(sensorType.toInt());
            registeredModels.clear();
        } else {
            throw new SensorEventSynchronizerFailedException("A listener of the sensor with ID "
                    + sensorType.toInt() + " has already been attached!");
        }
    }

    @Override
    public void detachOneListener(SensorType sensorType) {
        if (attachedListeners.contains(sensorType.toInt())) {
            attachedListeners.remove(sensorType.toInt());
            registeredModels.clear();
        }
    }

    @Override
    public void attachListenersList(List<SensorType> sensorTypesList)
            throws SensorEventSynchronizerFailedException {
        for (SensorType sensorType : sensorTypesList) {
            attachOneListener(sensorType);
        }
    }

    @Override
    public void detachListenersList(List<SensorType> sensorTypesList) {
        for (SensorType sensorType : sensorTypesList) {
            detachOneListener(sensorType);
        }
    }
}
