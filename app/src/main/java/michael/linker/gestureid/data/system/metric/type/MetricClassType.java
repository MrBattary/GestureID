package michael.linker.gestureid.data.system.metric.type;

import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public enum MetricClassType {
    ACCELEROMETER_METRIC_CLASS(BaseSensorType.ACCELEROMETER.toInt()),
    GYROSCOPE_METRIC_CLASS(BaseSensorType.GYROSCOPE.toInt()),
    MAGNETOMETER_METRIC_CLASS(BaseSensorType.MAGNETOMETER.toInt()),
    GRAVITY_METRIC_CLASS(CompositeSensorType.GRAVITY.toInt()),
    LINEAR_ACCELERATION_METRIC_CLASS(CompositeSensorType.LINEAR_ACCELERATION.toInt()),
    ROTATION_VECTOR_METRIC_CLASS(CompositeSensorType.ROTATION_VECTOR.toInt()),
    GEO_ROTATION_VECTOR_METRIC_CLASS(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR.toInt()),
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
