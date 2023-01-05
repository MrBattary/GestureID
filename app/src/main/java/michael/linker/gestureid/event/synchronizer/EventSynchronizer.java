package michael.linker.gestureid.event.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import michael.linker.gestureid.config.event.EventBufferConfiguration;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.event.buffer.mode.IEventBuffer;
import michael.linker.gestureid.event.buffer.overflow.EventBufferOverflowException;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.sensor.model.ASensorModel;
import michael.linker.gestureid.sensor.recognizer.SensorRecognizer;

public class EventSynchronizer implements IEventSynchronizer {
    private final IEventBuffer eventBuffer;

    private final Map<Integer, ASensorModel> registeredModels = new TreeMap<>();
    private final Set<Integer> attachedListeners = new TreeSet<>();

    public EventSynchronizer() {
        eventBuffer = EventBufferConfiguration.getActiveBuffer();
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
    private void tryToSynchronize() throws EventSynchronizerFailedException {
        if (registeredModels.keySet().equals(attachedListeners)) {
            SynchronizedEvent synchronizedEvent =
                    new SynchronizedEvent(
                            String.valueOf(System.currentTimeMillis()),
                            new ArrayList<>(registeredModels.values())
                    );
            try {
                eventBuffer.buffer(synchronizedEvent);
            } catch (EventBufferOverflowException e) {
                throw new EventSynchronizerFailedException("Accumulator is full!");
            }
            registeredModels.clear();
        }
    }

    @Override
    public void attach(SensorType sensorType)
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
    public void detach(SensorType sensorType) {
        if (attachedListeners.contains(sensorType.toInt())) {
            attachedListeners.remove(sensorType.toInt());
            registeredModels.clear();
        }
    }

    @Override
    public void attach(List<SensorType> sensorTypesList)
            throws EventSynchronizerFailedException {
        for (SensorType sensorType : sensorTypesList) {
            attach(sensorType);
        }
    }

    @Override
    public void detach(List<SensorType> sensorTypesList) {
        for (SensorType sensorType : sensorTypesList) {
            detach(sensorType);
        }
    }

    @Override
    public void detachAll() {
        attachedListeners.clear();
        registeredModels.clear();
    }
}
