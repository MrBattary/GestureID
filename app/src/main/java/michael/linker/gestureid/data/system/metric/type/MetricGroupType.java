package michael.linker.gestureid.data.system.metric.type;

public enum MetricGroupType {
    AXIS_X_METRIC_GROUP(1),
    AXIS_Y_METRIC_GROUP(2),
    AXIS_Z_METRIC_GROUP(3),
    AXIS_W_METRIC_GROUP(4),
    TIME_METRIC_GROUP(5),
    GENERAL_METRIC_GROUP(0),
    UNDEFINED_METRIC_GROUP(-1);

    private final int metricGroup;

    MetricGroupType(int metricGroup) {
        this.metricGroup = metricGroup;
    }

    public int toInt() {
        return metricGroup;
    }

    public static MetricGroupType fromInt(int value) {
        for (MetricGroupType metricType : MetricGroupType.values()) {
            if (metricType.toInt() == value) {
                return metricType;
            }
        }
        return UNDEFINED_METRIC_GROUP;
    }
}
