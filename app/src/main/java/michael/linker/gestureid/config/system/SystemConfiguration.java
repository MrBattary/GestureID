package michael.linker.gestureid.config.system;

import michael.linker.gestureid.config.sensor.SensorsConfiguration;

public final class SystemConfiguration {
    public static final class Type {
        public enum Status {
            ENABLED,
            DISABLED
        }
    }

    public static final class Build {
        public static boolean isAccelerometerMetricClassRequired() {
            return !SensorsConfiguration.Build.isAccelerometerDeactivated();
        }

        public static boolean isGyroscopeMetricClassRequired() {
            return !SensorsConfiguration.Build.isGyroscopeDeactivated();
        }

        public static boolean isMagnetometerMetricClassRequired() {
            return !SensorsConfiguration.Build.isMagnetometerDeactivated();
        }

        public static boolean isGravityMetricClassRequired() {
            return !SensorsConfiguration.Build.isGravityDeactivated();
        }

        public static boolean isLinearAccelerationMetricClassRequired() {
            return !SensorsConfiguration.Build.isLinearAccelerationDeactivated();
        }

        public static boolean isRotationVectorMetricClassRequired() {
            return !SensorsConfiguration.Build.isRotationVectorDeactivated();
        }

        public static boolean isGeoRotationVectorMetricClassRequired() {
            return !SensorsConfiguration.Build.isGeomagneticRotationVectorDeactivated();
        }
    }
}
