package michael.linker.gestrudeid.config;

import michael.linker.gestrudeid.BuildConfig;

/**
 * Wrapper for build variables
 * "build.gradle" file in the application main folder
 */
public final class SensorsBuildConfiguration {
    // Base
    public static boolean isAccelerometerRequired() {
        return BuildConfig.ACTIVATE_ACCELEROMETER;
    }

    public static boolean isGyroscopeRequired() {
        return BuildConfig.ACTIVATE_GYROSCOPE;
    }

    public static boolean isMagnetometerRequired() {
        return BuildConfig.ACTIVATE_MAGNETOMETER;
    }

    // Composite
    public static boolean isGravityRequired() {
        return BuildConfig.ACTIVATE_GRAVITY;
    }

    public static boolean isLinearAccelerationRequired() {
        return BuildConfig.ACTIVATE_LINEAR_ACCELERATION;
    }

    public static boolean isRotationVectorRequired() {
        return BuildConfig.ACTIVATE_ROTATION_VECTOR;
    }

    public static boolean isGeomagneticRotationVectorRequired() {
        return BuildConfig.ACTIVATE_GEOMAGNETIC_ROTATION_VECTOR;
    }
}
