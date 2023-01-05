package michael.linker.gestureid.event.accumulator.mode.active;

import java.util.List;

import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * Listener of active accumulator updates.
 */
public interface IActiveEventAccumulatorListener {
    /**
     * Calls by IActiveEventAccumulator impl when accumulator updates.
     *
     * @param synchronizedEventList event list.
     */
    void notifyAboutEvents(List<SynchronizedEvent> synchronizedEventList);
}
