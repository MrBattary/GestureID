package michael.linker.gestureid.data.event.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.data.event.accumulator.mode.IEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.data.sensor.model.ASensorModel;
import michael.linker.gestureid.data.sensor.recognizer.SensorRecognizer;

public class EventSynchronizer implements IEventSynchronizer {
    private final IEventAccumulator eventBuffer;

    private final Map<Integer, ASensorModel<Float>> registeredModels = new TreeMap<>();
    private final Set<Integer> attachedListeners = new TreeSet<>();

    public EventSynchronizer() {
        eventBuffer = EventAccumulatorConfiguration.getActiveAccumulator();
    }

    @Override
    public void registerEvent(ASensorModel<Float> sensorModel) throws
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
                eventBuffer.accumulate(synchronizedEvent);
            } catch (EventAccumulatorOverflowException e) {
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
