package michael.linker.gestureid.data.event.accumulator.mode.passive;

import michael.linker.gestureid.data.event.accumulator.mode.IEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.event.synchronizer.EventSynchronizerNotFoundException;

/**
 * A passive accumulator that simply stores data and does not take any active actions.
 */
public interface IPassiveEventAccumulator extends IEventAccumulator {
    /**
     * Returns, but does not remove last event from the accumulator.
     *
     * @return accumulated episode with single event.
     * @throws EventSynchronizerNotFoundException if accumulator is empty.
     */
    AccumulatedEpisode peek() throws EventSynchronizerNotFoundException;

    /**
     * Returns and removes last event from the accumulator.
     *
     * @return accumulated episode with single event.
     * @throws EventSynchronizerNotFoundException if accumulator is empty.
     */
    AccumulatedEpisode pull() throws EventSynchronizerNotFoundException;

    /**
     * Returns and removes all events from the accumulator.
     *
     * @return accumulated episode with list of events.
     * @throws EventSynchronizerNotFoundException if accumulator is empty.
     */
    AccumulatedEpisode pullAll() throws EventSynchronizerNotFoundException;
}
