package michael.linker.gestureid.analyzer.metric.type;

import michael.linker.gestureid.analyzer.sensor.SensorAxisType;

public enum MetricGroupType {
    AXIS_X_METRIC_GROUP(1),
    AXIS_Y_METRIC_GROUP(2),
    AXIS_Z_METRIC_GROUP(3),
    AXIS_W_METRIC_GROUP(4),
    /**
     * Other
     */
    TIME_METRIC_GROUP(5),
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

    public static SensorAxisType toSensorAxisType(MetricGroupType metricGroupType) {
        return switch (metricGroupType) {
            case AXIS_X_METRIC_GROUP -> SensorAxisType.X;
            case AXIS_Y_METRIC_GROUP -> SensorAxisType.Y;
            case AXIS_Z_METRIC_GROUP -> SensorAxisType.Z;
            case AXIS_W_METRIC_GROUP -> SensorAxisType.W;
            default -> SensorAxisType.UNDEFINED;
        };
    }

    public static MetricGroupType fromSensorAxisType(SensorAxisType axisType) {
        return switch (axisType) {
            case X -> AXIS_X_METRIC_GROUP;
            case Y -> AXIS_Y_METRIC_GROUP;
            case Z -> AXIS_Z_METRIC_GROUP;
            case W -> AXIS_W_METRIC_GROUP;
            default -> UNDEFINED_METRIC_GROUP;
        };
    }
}
