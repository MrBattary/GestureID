package michael.linker.gestureid.analyzer.metric.model;


import michael.linker.gestureid.analyzer.metric.type.MetricClassType;
import michael.linker.gestureid.analyzer.metric.type.MetricGroupType;
import michael.linker.gestureid.analyzer.metric.type.MetricType;

public class GetMetricModel {
    private final MetricClassType metricClass;
    private final MetricGroupType metricGroup;
    private final MetricType metricType;

    public GetMetricModel(
            MetricClassType metricClass,
            MetricGroupType metricGroup,
            MetricType metric) {
        this.metricClass = metricClass;
        this.metricGroup = metricGroup;
        this.metricType = metric;
    }

    public MetricClassType getMetricClass() {
        return metricClass;
    }

    public MetricGroupType getMetricGroup() {
        return metricGroup;
    }

    public MetricType getMetricType() {
        return metricType;
    }
}
