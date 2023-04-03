package michael.linker.gestureid.analyzer.metric.exception;

import michael.linker.gestureid.analyzer.metric.type.MetricClassType;

public class MetricNotRequiredException extends RuntimeException {
    private static final String CLASS = "Metric class %s not required and will not be processed!";

    public MetricNotRequiredException(MetricClassType classType) {
        super(String.format(CLASS, classType));
    }
}
