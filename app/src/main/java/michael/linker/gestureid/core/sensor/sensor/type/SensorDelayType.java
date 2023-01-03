package michael.linker.gestureid.core.sensor.sensor.type;

import michael.linker.gestureid.core.sensor.manager.AHardwareSensorManager;

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
        FASTEST = AHardwareSensorManager.SENSOR_DELAY_FASTEST;
        UI = AHardwareSensorManager.SENSOR_DELAY_UI;
        GAME = AHardwareSensorManager.SENSOR_DELAY_GAME;
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
