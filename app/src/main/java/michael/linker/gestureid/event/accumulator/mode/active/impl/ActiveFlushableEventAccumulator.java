package michael.linker.gestureid.event.accumulator.mode.active.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.event.accumulator.mode.active.ABaseActiveEventAccumulator;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveFlushableEventAccumulator;
import michael.linker.gestureid.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.event.accumulator.overflow.EventAccumulatorOverflowStrategyProvider;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * Accumulator with internal storage that notifies listeners with list of events.
 */
public class ActiveFlushableEventAccumulator extends ABaseActiveEventAccumulator implements
        IActiveFlushableEventAccumulator {
    private final Deque<SynchronizedEvent> eventDeque;

    public ActiveFlushableEventAccumulator() {
        eventDeque = new ArrayDeque<>();
    }

    @Override
    public void flush() {
        AccumulatedEpisode episode = new AccumulatedEpisode(new ArrayList<>(eventDeque));
        eventDeque.clear();
        for (IActiveEventAccumulatorListener listener : super.listenerSet) {
            listener.notifyAboutEpisode(episode);
        }
    }

    @Override
    public void accumulate(SynchronizedEvent synchronizedEvent) throws
            EventAccumulatorOverflowException {
        applyOverflowStrategy();
        eventDeque.add(synchronizedEvent);
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
