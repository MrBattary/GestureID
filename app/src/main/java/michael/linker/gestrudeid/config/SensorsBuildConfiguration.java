package michael.linker.gestrudeid.config;

import michael.linker.gestrudeid.BuildConfig;

/**
 * Wrapper for the sensors build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestrudeid.sensor
 */
public final class SensorsBuildConfiguration {
    /**
     * Base
     */
    public static boolean isAccelerometerDeactivated() {
        return BuildConfig.DEACTIVATE_ACCELEROMETER;
    }

    public static boolean isGyroscopeDeactivated() {
        return BuildConfig.DEACTIVATE_GYROSCOPE;
    }

    public static boolean isMagnetometerDeactivated() {
        return BuildConfig.DEACTIVATE_MAGNETOMETER;
    }

    /**
     * Composite
     */
    public static boolean isGravityDeactivated() {
        return BuildConfig.DEACTIVATE_GRAVITY;
    }

    public static boolean isLinearAccelerationDeactivated() {
        return BuildConfig.DEACTIVATE_LINEAR_ACCELERATION;
    }

    public static boolean isRotationVectorDeactivated() {
        return BuildConfig.DEACTIVATE_ROTATION_VECTOR;
    }

    public static boolean isGeomagneticRotationVectorDeactivated() {
        return BuildConfig.DEACTIVATE_GEOMAGNETIC_ROTATION_VECTOR;
    }
}
