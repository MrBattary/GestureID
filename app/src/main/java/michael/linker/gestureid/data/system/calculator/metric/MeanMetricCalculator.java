package michael.linker.gestureid.data.system.calculator.metric;

import michael.linker.gestureid.data.system.calculator.model.RearrangedAccumulatedEpisode;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;

public class MeanMetricCalculator implements IMetricCalculator<Double> {


    @Override
    public Double calculate(RearrangedAccumulatedEpisode rearrangedEpisode,
            MetricClassType metricClass, MetricGroupType metricGroup) {
        return rearrangedEpisode.getListData(metricClass,
                metricGroup).stream().mapToDouble(l -> l).summaryStatistics().getAverage();
    }
}
