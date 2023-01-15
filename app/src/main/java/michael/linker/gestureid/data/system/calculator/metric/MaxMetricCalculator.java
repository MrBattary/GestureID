package michael.linker.gestureid.data.system.calculator.metric;

import java.util.Collections;

import michael.linker.gestureid.data.system.calculator.model.RearrangedAccumulatedEpisode;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;

public class MaxMetricCalculator implements IMetricCalculator<Double> {
    @Override
    public Double calculate(RearrangedAccumulatedEpisode rearrangedEpisode,
            MetricClassType metricClass, MetricGroupType metricGroup) {
        return Collections.max(rearrangedEpisode.getListData(metricClass, metricGroup));
    }
}
