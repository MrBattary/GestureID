package michael.linker.gestureid.data.system.metric.type;

public enum MetricType {
    /**
     * Value 'as is'.
     */
    VALUE_METRIC(0),
    /**
     * Min value.
     */
    MIN_METRIC(1),
    /**
     * Max value.
     */
    MAX_METRIC(2),
    /**
     * spread = max - min
     */
    SPREAD_METRIC(3),
    UNDEFINED_METRIC(-1);

    private final int metric;

    MetricType(int metric) {
        this.metric = metric;
    }

    public int toInt() {
        return metric;
    }

    public static MetricType fromInt(int value) {
        for (MetricType metricType : MetricType.values()) {
            if (metricType.toInt() == value) {
                return metricType;
            }
        }
        return UNDEFINED_METRIC;
    }
}
