package michael.linker.gestureid.data.system.calculator.metric;

import michael.linker.gestureid.data.system.calculator.model.RearrangedAccumulatedEpisode;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;

public class DefaultMetricCalculator implements IMetricCalculator<Double> {
    @Override
    public Double calculate(RearrangedAccumulatedEpisode rearrangedEpisode,
            MetricClassType metricClass, MetricGroupType metricGroup) {
        throw new CalculatorNotImplementedException();
    }
}
