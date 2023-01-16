package michael.linker.gestureid.data.system.calculator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.calculator.metric.DefaultMetricCalculator;
import michael.linker.gestureid.data.system.calculator.metric.IMetricCalculator;
import michael.linker.gestureid.data.system.calculator.metric.MaxMetricCalculator;
import michael.linker.gestureid.data.system.calculator.metric.MeanMetricCalculator;
import michael.linker.gestureid.data.system.calculator.metric.MinMetricCalculator;
import michael.linker.gestureid.data.system.calculator.metric.SpreadMetricCalculator;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.calculator.model.RearrangedAccumulatedEpisode;
import michael.linker.gestureid.data.system.metric.Metric;
import michael.linker.gestureid.data.system.metric.model.AddMetricModel;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;
import michael.linker.gestureid.data.system.metric.type.MetricType;

public class SystemCalculator implements ISystemCalculator {
    private final Map<MetricType, IMetricCalculator<Double>> metricCalculatorMap;
    private static final IMetricCalculator<Double> DEFAULT_METRIC_CALCULATOR =
            new DefaultMetricCalculator();

    public SystemCalculator() {
        metricCalculatorMap = new HashMap<>();
        metricCalculatorMap.put(MetricType.ARITHMETIC_MEAN, new MeanMetricCalculator());
        metricCalculatorMap.put(MetricType.MIN_METRIC, new MinMetricCalculator());
        metricCalculatorMap.put(MetricType.MAX_METRIC, new MaxMetricCalculator());
        metricCalculatorMap.put(MetricType.SPREAD_METRIC, new SpreadMetricCalculator());
    }

    @Override
    public EpisodeMetrics calculateEpisodeMetrics(AccumulatedEpisode episode) {
        RearrangedAccumulatedEpisode rearrangedEpisode = new RearrangedAccumulatedEpisode(episode);
        Set<MetricClassType> requiredMetricClassSet = getRequiredMetricClasses();
        Metric<Double> metric = new Metric<>();

        for (MetricClassType metricClass : requiredMetricClassSet) {
            Set<MetricGroupType> requiredMetricGroupSet =
                    SystemConfiguration.Build.Group.getMetricGroupForClass(metricClass);

            for (MetricGroupType metricGroup : requiredMetricGroupSet) {
                Set<MetricType> requiredMetricSet =
                        SystemConfiguration.Build.Metric.getMetricForGroup(metricGroup);

                for (MetricType metricType : requiredMetricSet) {
                    Double metricValue = getMetricCalculator(metricType)
                            .calculate(rearrangedEpisode, metricClass, metricGroup);
                    metric.putMetric(
                            new AddMetricModel<>(
                                    metricClass,
                                    metricGroup,
                                    metricType,
                                    metricValue));
                }
            }
        }

        return new EpisodeMetrics(metric);
    }

    private Set<MetricClassType> getRequiredMetricClasses() {
        Set<MetricClassType> requiredMetricClassSet = new HashSet<>();
        for (MetricClassType metricClassType : MetricClassType.values()) {
            if (SystemConfiguration.Build.Class.isMetricClassRequired(metricClassType)) {
                requiredMetricClassSet.add(metricClassType);
            }
        }
        return requiredMetricClassSet;
    }

    private IMetricCalculator<Double> getMetricCalculator(MetricType metricType) {
        return metricCalculatorMap.getOrDefault(metricType, DEFAULT_METRIC_CALCULATOR);
    }
}
