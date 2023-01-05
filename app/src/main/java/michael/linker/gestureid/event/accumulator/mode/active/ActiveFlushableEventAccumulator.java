package michael.linker.gestureid.event.accumulator.mode.active;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.event.accumulator.overflow.EventAccumulatorOverflowStrategyProvider;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * Accumulator with internal storage storage that notifies listeners with list of events.
 */
public class ActiveFlushableEventAccumulator implements IActiveFlushableEventAccumulator {
    private final Set<IActiveEventBufferListener> listenerSet;
    private final Deque<SynchronizedEvent> eventDeque;

    public ActiveFlushableEventAccumulator() {
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
    public void accumulate(SynchronizedEvent synchronizedEvent) throws
            EventAccumulatorOverflowException {
        applyOverflowStrategy();
        eventDeque.add(synchronizedEvent);
        flush();
    }

    @Override
    public int getMaxSize() {
        return EventAccumulatorConfiguration.Build.getAccumulatorMaxSize();
    }

    @Override
    public int getSize() {
        return eventDeque.size();
    }

    private void applyOverflowStrategy() throws EventAccumulatorOverflowException {
        if (getSize() >= getMaxSize()) {
            EventAccumulatorOverflowStrategyProvider.getOverflowStrategy().execute(eventDeque);
        }
    }
}
