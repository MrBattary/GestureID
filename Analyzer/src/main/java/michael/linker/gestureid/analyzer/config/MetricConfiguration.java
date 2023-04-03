package michael.linker.gestureid.analyzer.config;

public class MetricConfiguration {
    public static boolean isAccelerometerMetricClassRequired() {
        return true;
    }

    public static boolean isGyroscopeMetricClassRequired() {
        return true;
    }

    public static boolean isMagnetometerMetricClassRequired() {
        return false;
    }

    public static boolean isGravityMetricClassRequired() {
        return true;
    }

    public static boolean isLinearAccelerationMetricClassRequired() {
        return true;
    }

    public static boolean isRotationVectorMetricClassRequired() {
        return false;
    }

    public static boolean isGeoRotationVectorMetricClassRequired() {
        return false;
    }
}
