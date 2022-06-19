package michael.linker.gestureid.config;

import michael.linker.gestureid.BuildConfig;
import michael.linker.gestureid.sensor.type.SensorDelayType;

/**
 * Wrapper for the listener build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestureid.sensor.listener
 */
public final class ListenersBuildConfiguration {
    public static SensorDelayType getSensorDelay() {
        return new SensorDelayType(BuildConfig.SENSOR_DELAY);
    }
}
