package michael.linker.gestureid.data.system.processor;

import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.processor.type.SystemProcessorResult;

public interface ISystemProcessor {
    /**
     * Proceed accumulated episode to the processor.
     *
     * @param accumulatedEpisode accumulated episode.
     * @return success or failure.
     */
    SystemProcessorResult proceed(AccumulatedEpisode accumulatedEpisode);

    /**
     * Notifies processor with that auth was acquired from the user.
     */
    void authAcquired();

    /**
     * Saves nodes data.
     */
    void saveData();
}
