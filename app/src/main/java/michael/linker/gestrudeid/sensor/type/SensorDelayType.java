package michael.linker.gestrudeid.sensor.type;

import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;

/**
 * Delay between event registration
 */
public class SensorDelayType {
    /**
     * Get sensor data as fast as possible
     */
    public final static Integer FASTEST;
    /**
     * Rate suitable for screen orientation changes
     */
    public final static Integer NORMAL;
    /**
     * Rate suitable for the user interface
     */
    public final static Integer UI;

    static {
        FASTEST = ASensorManager.SENSOR_DELAY_FASTEST;
        NORMAL = ASensorManager.SENSOR_DELAY_NORMAL;
        UI = ASensorManager.SENSOR_DELAY_UI;
    }

    final Integer sensorDelay;

    public SensorDelayType(final Integer sensorDelay) {
        this.sensorDelay = sensorDelay;
    }

    public Integer toInt() {
        return sensorDelay;
    }
}
