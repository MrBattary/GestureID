package michael.linker.gestureid.event.buffer.mode.active;

import java.util.HashSet;
import java.util.Set;

import michael.linker.gestureid.config.EventsBuildConfiguration;
import michael.linker.gestureid.event.buffer.overflow.EventBufferOverflowException;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventListOfModels;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventSingleModel;

/**
 * Buffer without storage that notifies listeners.
 */
public class ActiveEventBuffer implements IActiveEventBuffer {
    private final Set<IActiveEventBufferListener> listenerSet;

    public ActiveEventBuffer() {
        listenerSet = new HashSet<>();
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
        notifyListeners(synchronizedSensorModel);
    }

    @Override
    public void buffer(SynchronizedEventListOfModels synchronizedSensorModels)
            throws EventBufferOverflowException {
        notifyListeners(synchronizedSensorModels);
    }

    @Override
    public int getMaxSize() {
        return EventsBuildConfiguration.Buffer.getBufferMaxSize();
    }

    @Override
    public int getSize() {
        return 0;
    }

    private void notifyListeners(SynchronizedEvent event) {
        for (IActiveEventBufferListener listener : listenerSet) {
            listener.notifyAboutEvent(event);
        }
    }
}
