package michael.linker.gestrudeid.sensor.synchronizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import michael.linker.gestrudeid.sensor.model.SensorModel;
import michael.linker.gestrudeid.sensor.types.SensorType;

public class SensorEventSynchronizer implements ISensorEventSynchronizer {
    private final HashMap<Integer, SensorModel> models = new HashMap<>();
    private final Set<Integer> attachedListeners = new HashSet<>();

    @Override
    public void registerEvent(SensorModel sensorModel) throws
            SensorEventSynchronizerNotFoundException, SensorEventSynchronizerFailedException {
        final int sensorIdFromModel = sensorModel.getSensorType().toInt();

        if (attachedListeners.contains(sensorIdFromModel)) {
            if (!models.containsKey(sensorIdFromModel)) {
                models.put(sensorIdFromModel, sensorModel);
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

    // TODO
    private void tryToSynchronize() {

    }

    // TODO Handle 'changed' variable
    @Override
    public void attachOneListener(SensorType sensorType)
            throws SensorEventSynchronizerFailedException {
        if (!attachedListeners.contains(sensorType.toInt())) {
            attachedListeners.add(sensorType.toInt());
        } else {
            throw new SensorEventSynchronizerFailedException("A listener of the sensor with ID "
                    + sensorType.toInt() + " has already been attached!");
        }
    }

    // TODO Handle 'changed' variable
    @Override
    public void detachOneListener(SensorType sensorType) {
        attachedListeners.remove(sensorType.toInt());
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
