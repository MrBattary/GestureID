package michael.linker.gestureid.data.system.metric.exception;

import michael.linker.gestureid.data.system.metric.type.MetricClassType;

public class MetricNotRequiredException extends RuntimeException {
    private static final String CLASS = "Metric class %s not required and will not be processed!";

    public MetricNotRequiredException(MetricClassType classType) {
        super(String.format(CLASS, classType));
    }
}
