package michael.linker.gestureid.data.system.calculator.metric;

import java.util.Collections;
import java.util.List;

import michael.linker.gestureid.data.system.calculator.model.RearrangedAccumulatedEpisode;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;

public class SpreadMetricCalculator implements IMetricCalculator<Double> {
    @Override
    public Double calculate(RearrangedAccumulatedEpisode rearrangedEpisode,
            MetricClassType metricClass, MetricGroupType metricGroup) {
        List<Double> data = rearrangedEpisode.getListData(metricClass, metricGroup);
        return (Collections.max(data) - Collections.min(data));
    }
}
