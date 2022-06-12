package michael.linker.gestrudeid.config;

import michael.linker.gestrudeid.BuildConfig;
import michael.linker.gestrudeid.sensor.type.SensorDelayType;

/**
 * Wrapper for the listener build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestrudeid.sensor.listener
 */
public final class ListenersBuildConfiguration {
    public static SensorDelayType getSensorDelay() {
        return new SensorDelayType(BuildConfig.SENSOR_DELAY);
    }
}
