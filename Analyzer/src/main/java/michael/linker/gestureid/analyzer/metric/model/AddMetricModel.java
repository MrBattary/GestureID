package michael.linker.gestureid.analyzer.metric.model;


import michael.linker.gestureid.analyzer.metric.type.MetricClassType;
import michael.linker.gestureid.analyzer.metric.type.MetricGroupType;
import michael.linker.gestureid.analyzer.metric.type.MetricType;

public class AddMetricModel<T> extends GetMetricModel {
    private final T metricValue;

    public AddMetricModel(
            MetricClassType metricClass,
            MetricGroupType metricGroup,
            MetricType metric,
            T metricValue) {
        super(metricClass, metricGroup, metric);
        this.metricValue = metricValue;
    }

    public T getMetricValue() {
        return metricValue;
    }
}
