package michael.linker.gestureid.data.system.calculator.metric;

import michael.linker.gestureid.data.system.calculator.model.RearrangedAccumulatedEpisode;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;

public interface IMetricCalculator<T> {
    /**
     * Calculate metric for the rearranged episode.
     *
     * @param rearrangedEpisode rearranged accumulated episode.
     * @param metricClass       class for which metric will be calculated.
     * @param metricGroup       group for which metric will be calculated.
     * @return metric value.
     */
    T calculate(RearrangedAccumulatedEpisode rearrangedEpisode,
            MetricClassType metricClass,
            MetricGroupType metricGroup);
}
