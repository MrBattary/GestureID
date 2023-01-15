package michael.linker.gestureid.data.system.calculator;

import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;

public interface ISystemCalculator {
    /**
     * Calculate episode metrics according to the configuration.
     *
     * @param episode accumulated episode.
     * @return calculated metrics.
     */
    EpisodeMetrics calculateEpisodeMetrics(AccumulatedEpisode episode);
}
