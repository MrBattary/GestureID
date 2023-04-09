package michael.linker.gestureid.analyzer.metric.exception;


import michael.linker.gestureid.analyzer.metric.type.MetricClassType;
import michael.linker.gestureid.analyzer.metric.type.MetricGroupType;
import michael.linker.gestureid.analyzer.metric.type.MetricType;

public class MetricNotFoundException extends RuntimeException {
    private static final String CLASS = "Metric class %s was not found!";
    private static final String GROUP = "Metric group %s was not found!";
    private static final String METRIC = "Metric %s was not found!";

    public MetricNotFoundException(MetricClassType classType) {
        super(String.format(CLASS, classType));
    }

    public MetricNotFoundException(MetricGroupType groupType) {
        super(String.format(GROUP, groupType));
    }

    public MetricNotFoundException(MetricType metricType) {
        super(String.format(METRIC, metricType));
    }
}
