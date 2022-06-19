package michael.linker.gestureid.sensor.type;

import michael.linker.gestureid.sensor.wrapper.manager.ASensorManager;

/**
 * Delay between event registration
 */
public class SensorDelayType {
    /**
     * Get sensor data as fast as possible, 0 microseconds delay
     */
    public final static Integer FASTEST;
    /**
     * Rate suitable for games, 20000 microseconds delay
     */
    public static final Integer GAME;
    /**
     * Rate suitable for the user interface, 60000 microseconds delay
     */
    public final static Integer UI;
    /**
     * Rate suitable for screen orientation changes, 200000 microseconds delay
     */
    public final static Integer NORMAL;

    static {
        FASTEST = ASensorManager.SENSOR_DELAY_FASTEST;
        UI = ASensorManager.SENSOR_DELAY_UI;
        GAME = ASensorManager.SENSOR_DELAY_GAME;
        NORMAL = ASensorManager.SENSOR_DELAY_NORMAL;
    }

    final Integer sensorDelay;

    public SensorDelayType(final Integer sensorDelay) {
        this.sensorDelay = sensorDelay;
    }

    public Integer toInt() {
        return sensorDelay;
    }
}
