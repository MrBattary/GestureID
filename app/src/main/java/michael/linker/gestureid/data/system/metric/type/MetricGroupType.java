package michael.linker.gestureid.data.system.metric.type;

import michael.linker.gestureid.core.sensor.sensor.type.SensorAxisType;

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
        switch (metricGroupType) {
            case AXIS_X_METRIC_GROUP:
                return SensorAxisType.X;
            case AXIS_Y_METRIC_GROUP:
                return SensorAxisType.Y;
            case AXIS_Z_METRIC_GROUP:
                return SensorAxisType.Z;
            case AXIS_W_METRIC_GROUP:
                return SensorAxisType.W;
            default:
                return null;
        }
    }

    public static MetricGroupType fromSensorAxisType(SensorAxisType axisType) {
        switch (axisType) {
            case X:
                return AXIS_X_METRIC_GROUP;
            case Y:
                return AXIS_Y_METRIC_GROUP;
            case Z:
                return AXIS_Z_METRIC_GROUP;
            case W:
                return AXIS_W_METRIC_GROUP;
            default:
                return UNDEFINED_METRIC_GROUP;
        }
    }
}
