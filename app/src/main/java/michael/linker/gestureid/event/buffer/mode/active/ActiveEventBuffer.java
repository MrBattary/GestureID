package michael.linker.gestureid.event.buffer.mode.active;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import michael.linker.gestureid.config.EventBufferConfiguration;
import michael.linker.gestureid.event.buffer.overflow.EventBufferOverflowException;
import michael.linker.gestureid.event.buffer.overflow.EventBufferOverflowStrategyProvider;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventListOfModels;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventSingleModel;

/**
 * Buffer without storage that notifies listeners.
 */
public class ActiveEventBuffer implements IActiveEventBuffer {
    private final Set<IActiveEventBufferListener> listenerSet;
    private final Deque<SynchronizedEvent> eventDeque;

    public ActiveEventBuffer() {
        listenerSet = new HashSet<>();
        eventDeque = new ArrayDeque<>();
    }

    @Override
    public void flush() {
        List<SynchronizedEvent> eventList = new ArrayList<>(eventDeque);
        eventDeque.clear();
        for (IActiveEventBufferListener listener : listenerSet) {
            listener.notifyAboutEvents(eventList);
        }
    }

    @Override
    public void subscribe(IActiveEventBufferListener listener) {
        listenerSet.add(listener);
    }

    @Override
    public void unsubscribe(IActiveEventBufferListener listener) {
        listenerSet.remove(listener);
    }

    @Override
    public void unsubscribeAll() {
        listenerSet.clear();
    }

    @Override
    public void buffer(SynchronizedEventSingleModel synchronizedSensorModel)
            throws EventBufferOverflowException {
        applyOverflowStrategy();
        eventDeque.add(synchronizedSensorModel);
    }

    @Override
    public void buffer(SynchronizedEventListOfModels synchronizedSensorModels)
            throws EventBufferOverflowException {
        applyOverflowStrategy();
        eventDeque.add(synchronizedSensorModels);
    }

    @Override
    public int getMaxSize() {
        return EventBufferConfiguration.Build.getBufferMaxSize();
    }

    @Override
    public int getSize() {
        return eventDeque.size();
    }

    private void applyOverflowStrategy() throws EventBufferOverflowException {
        if (getSize() >= getMaxSize()) {
            EventBufferOverflowStrategyProvider.getOverflowStrategy().execute(eventDeque);
        }
    }
}
