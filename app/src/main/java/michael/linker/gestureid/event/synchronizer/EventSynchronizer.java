package michael.linker.gestureid.event.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import michael.linker.gestureid.event.buffer.IEventBuffer;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventListOfModels;
import michael.linker.gestureid.sensor.model.ASensorModel;
import michael.linker.gestureid.sensor.recognizer.SensorRecognizer;
import michael.linker.gestureid.sensor.type.SensorType;

public class EventSynchronizer implements IEventSynchronizer {
    private final IEventBuffer eventBuffer;

    private final Map<Integer, ASensorModel> registeredModels = new TreeMap<>();
    private final Set<Integer> attachedListeners = new TreeSet<>();

    public EventSynchronizer(IEventBuffer eventBuffer) {
        this.eventBuffer = eventBuffer;
    }

    @Override
    public void registerEvent(ASensorModel sensorModel) throws
            EventSynchronizerNotFoundException, EventSynchronizerFailedException {
        final int sensorIdFromModel = sensorModel.getSensorType().toInt();
        tryToSynchronize();

        if (attachedListeners.contains(sensorIdFromModel)) {
            boolean isEventAlreadyRegistered = registeredModels.containsKey(sensorIdFromModel);
            registeredModels.put(sensorIdFromModel, sensorModel);
            if (isEventAlreadyRegistered) {
                throw new EventSynchronizerFailedException(
                        "Event for the listener of the sensor "
                                + SensorRecognizer.recognizeSensorById(sensorIdFromModel)
                                + " has already been registered and was overwritten!");
            }
        } else {
            throw new EventSynchronizerNotFoundException("A listener of the sensor "
                    + SensorRecognizer.recognizeSensorById(sensorIdFromModel)
                    + " has not been attached!");
        }
    }

    /**
     * Syncs if each listener has submitted their own model
     */
    private void tryToSynchronize() {
        if (registeredModels.keySet().equals(attachedListeners)) {
            SynchronizedEventListOfModels synchronizedEvent =
                    new SynchronizedEventListOfModels(
                            String.valueOf(System.currentTimeMillis()),
                            new ArrayList<>(registeredModels.values())
                    );
            eventBuffer.buffer(synchronizedEvent);
            registeredModels.clear();
        }
    }

    @Override
    public void attachOneListener(SensorType sensorType)
            throws EventSynchronizerFailedException {
        if (!attachedListeners.contains(sensorType.toInt())) {
            attachedListeners.add(sensorType.toInt());
            registeredModels.clear();
        } else {
            throw new EventSynchronizerFailedException("A listener of the sensor with ID "
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
            throws EventSynchronizerFailedException {
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
