package michael.linker.gestureid.data.event.accumulator.mode.active.impl;

import android.util.Log;

import java.util.List;

import michael.linker.gestureid.data.event.accumulator.mode.active.ABaseActiveEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;

/**
 * Accumulator without internal storage.
 */
public class ActiveDistributableEventAccumulator extends ABaseActiveEventAccumulator implements
        IActiveEventAccumulator {
    private static final String TAG = ActiveDistributableEventAccumulator.class.getCanonicalName();
    private SynchronizedEvent accumulatedEvent;

    public ActiveDistributableEventAccumulator() {
        accumulatedEvent = null;
    }

    @Override
    public void accumulate(SynchronizedEvent synchronizedEvent)
            throws EventAccumulatorOverflowException {
        accumulatedEvent = synchronizedEvent;
        distribute();
    }

    private void distribute() {
        AccumulatedEpisode episode = new AccumulatedEpisode(List.of(accumulatedEvent));
        accumulatedEvent = null;
        for (IActiveEventAccumulatorListener listener : super.listenerSet) {
            Log.i(TAG, "Accumulated episode distributed");
            listener.notifyAboutEpisode(episode);
        }
    }

    @Override
    public int getMaxSize() {
        return 1;
    }

    @Override
    public int getSize() {
        return accumulatedEvent == null ? 0 : 1;
    }
}
