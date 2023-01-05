package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.sensor.manager.ISensorManager;
import michael.linker.gestureid.sensor.manager.SensorManager;

public class SensorManagerConfiguration {
    private static ISensorManager sensorManager = null;

    /**
     * Get instance of the World with the hardware SensorManager.
     *
     * @return World instance.
     */
    public static ISensorManager getManager() {
        if (sensorManager == null) {
            sensorManager = new SensorManager();
        }
        return sensorManager;
    }
}
