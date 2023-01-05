package michael.linker.gestureid.event.accumulator.mode.active.impl;

import java.util.List;

import michael.linker.gestureid.event.accumulator.mode.active.ABaseActiveEventAccumulator;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveEventAccumulator;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * Accumulator without internal storage.
 */
public class ActiveDistributableEventAccumulator extends ABaseActiveEventAccumulator implements
        IActiveEventAccumulator {
    private SynchronizedEvent accumulatedEvent;

    public ActiveDistributableEventAccumulator() {
        accumulatedEvent = null;
    }

    @Override
    public void accumulate(SynchronizedEvent synchronizedEvent)
            throws EventAccumulatorOverflowException {
        accumulatedEvent = synchronizedEvent;
        flush();
    }

    private void flush() {
        List<SynchronizedEvent> eventList = List.of(accumulatedEvent);
        accumulatedEvent = null;
        for (IActiveEventAccumulatorListener listener : super.listenerSet) {
            listener.notifyAboutEvents(eventList);
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
