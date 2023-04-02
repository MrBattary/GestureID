package michael.linker.gestureid.core.sensor.sensor.type;

import michael.linker.gestureid.core.sensor.manager.AHardwareSensorManager;

/**
 * Delay between event registration
 */
public class SensorDelayType {
    /**
     * Get sensor data as fast as possible, 0 microseconds delay
     */
    public static final Integer FASTEST;
    /**
     * 10000 microseconds delay
     */
    public static final Integer VERY_FAST;
    /**
     * Rate suitable for games, 20000 microseconds delay
     */
    public static final Integer GAME;
    /**
     * Rate suitable for the user interface, 60000 microseconds delay
     */
    public static final Integer UI;
    /**
     * Rate suitable for screen orientation changes, 200000 microseconds delay
     */
    public static final Integer NORMAL;

    static {
        FASTEST = AHardwareSensorManager.SENSOR_DELAY_FASTEST;
        VERY_FAST = AHardwareSensorManager.SENSOR_DELAY_GAME / 2;
        GAME = AHardwareSensorManager.SENSOR_DELAY_GAME;
        UI = AHardwareSensorManager.SENSOR_DELAY_UI;
        NORMAL = AHardwareSensorManager.SENSOR_DELAY_NORMAL;
    }

    final Integer sensorDelay;

    public SensorDelayType(final Integer sensorDelay) {
        this.sensorDelay = sensorDelay;
    }

    public Integer toInt() {
        return sensorDelay;
    }
}
