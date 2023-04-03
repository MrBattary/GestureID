package michael.linker.gestureid.analyzer.metric.type;

public enum MetricClassType {
    ACCELEROMETER_METRIC_CLASS(1),
    GYROSCOPE_METRIC_CLASS(4),
    MAGNETOMETER_METRIC_CLASS(2),
    GRAVITY_METRIC_CLASS(9),
    LINEAR_ACCELERATION_METRIC_CLASS(10),
    ROTATION_VECTOR_METRIC_CLASS(11),
    GEO_ROTATION_VECTOR_METRIC_CLASS(20),
    GENERAL_METRIC_CLASS(0),
    UNDEFINED_METRIC_CLASS(-1);

    private final int metricClass;

    MetricClassType(int metricClass) {
        this.metricClass = metricClass;
    }

    public int toInt() {
        return metricClass;
    }

    public static MetricClassType fromInt(int value) {
        for (MetricClassType metricType : MetricClassType.values()) {
            if (metricType.toInt() == value) {
                return metricType;
            }
        }
        return UNDEFINED_METRIC_CLASS;
    }
}
