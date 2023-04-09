package michael.linker.gestureid.analyzer.config;

import michael.linker.gestureid.analyzer.config.properties.PropertiesKey;
import michael.linker.gestureid.analyzer.config.properties.PropertiesProvider;

public class MetricConfiguration {
    public static boolean isAccelerometerMetricClassRequired() {
        return PropertiesProvider.getBooleanProperty(PropertiesKey.IS_ACCELEROMETER_METRIC_CLASS_REQUIRED_KEY);
    }

    public static boolean isGyroscopeMetricClassRequired() {
        return PropertiesProvider.getBooleanProperty(PropertiesKey.IS_GYROSCOPE_METRIC_CLASS_REQUIRED_KEY);
    }

    public static boolean isMagnetometerMetricClassRequired() {
        return PropertiesProvider.getBooleanProperty(PropertiesKey.IS_MAGNETOMETER_METRIC_CLASS_REQUIRED_KEY);
    }

    public static boolean isGravityMetricClassRequired() {
        return PropertiesProvider.getBooleanProperty(PropertiesKey.IS_GRAVITY_METRIC_CLASS_REQUIRED_KEY);
    }

    public static boolean isLinearAccelerationMetricClassRequired() {
        return PropertiesProvider.getBooleanProperty(PropertiesKey.IS_LINEAR_ACCELERATION_METRIC_CLASS_REQUIRED_KEY);
    }

    public static boolean isRotationVectorMetricClassRequired() {
        return PropertiesProvider.getBooleanProperty(PropertiesKey.IS_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY);
    }

    public static boolean isGeoRotationVectorMetricClassRequired() {
        return PropertiesProvider.getBooleanProperty(PropertiesKey.IS_GEO_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY);
    }
}
