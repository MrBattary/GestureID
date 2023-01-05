package michael.linker.gestureid.event.accumulator.mode.active;

import michael.linker.gestureid.event.accumulator.model.AccumulatedEpisode;

/**
 * Listener of active accumulator updates.
 */
public interface IActiveEventAccumulatorListener {
    /**
     * Calls by IActiveEventAccumulator impl when accumulator updates.
     *
     * @param accumulatedEpisode accumulated episode with list of events..
     */
    void notifyAboutEpisode(AccumulatedEpisode accumulatedEpisode);
}
