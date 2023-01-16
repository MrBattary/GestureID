package michael.linker.gestureid.data.event.accumulator.mode.active.impl;

import android.util.Log;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.ArrayList;
import java.util.Queue;

import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.data.event.accumulator.mode.active.ABaseActiveEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveFlushableEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.data.event.accumulator.overflow.EventAccumulatorOverflowStrategyProvider;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;

/**
 * Accumulator with internal storage that notifies listeners with list of events.
 */
public class ActiveFlushableEventAccumulator extends ABaseActiveEventAccumulator implements
        IActiveFlushableEventAccumulator {
    private static final String TAG = ActiveFlushableEventAccumulator.class.getCanonicalName();
    private boolean accumulationEnabled;
    private final Queue<SynchronizedEvent> eventDeque;

    public ActiveFlushableEventAccumulator() {
        eventDeque = new CircularFifoQueue<>(getMaxSize());
        accumulationEnabled = false;
    }

    @Override
    public void startAccumulation() {
        accumulationEnabled = true;
        Log.i(TAG, "Accumulation in the accumulator has been started.");
    }

    @Override
    public void flush() {
        accumulationEnabled = false;
        AccumulatedEpisode episode = new AccumulatedEpisode(new ArrayList<>(eventDeque));
        eventDeque.clear();
        for (IActiveEventAccumulatorListener listener : super.listenerSet) {
            Log.i(TAG, "Accumulated episode flushed to the listener.");
            listener.notifyAboutEpisode(episode);
        }
    }

    @Override
    public void accumulate(SynchronizedEvent synchronizedEvent) throws
            EventAccumulatorOverflowException {
        if (accumulationEnabled) {
            applyOverflowStrategy();
            eventDeque.add(synchronizedEvent);
        }
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
