package michael.linker.gestrudeid.config;

import michael.linker.gestrudeid.BuildConfig;

/**
 * Wrapper for the sensors build variables
 * "build.gradle" file in the application main folder
 */
public final class SensorsBuildConfiguration {
    /**
     * Base
     */
    public static boolean isAccelerometerActivated() {
        return BuildConfig.ACTIVATE_ACCELEROMETER;
    }

    public static boolean isGyroscopeActivated() {
        return BuildConfig.ACTIVATE_GYROSCOPE;
    }

    public static boolean isMagnetometerActivated() {
        return BuildConfig.ACTIVATE_MAGNETOMETER;
    }

    /**
     * Composite
     */
    public static boolean isGravityActivated() {
        return BuildConfig.ACTIVATE_GRAVITY;
    }

    public static boolean isLinearAccelerationActivated() {
        return BuildConfig.ACTIVATE_LINEAR_ACCELERATION;
    }

    public static boolean isRotationVectorActivated() {
        return BuildConfig.ACTIVATE_ROTATION_VECTOR;
    }

    public static boolean isGeomagneticRotationVectorActivated() {
        return BuildConfig.ACTIVATE_GEOMAGNETIC_ROTATION_VECTOR;
    }
}
