package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.sensor.manager.ISensorManager;
import michael.linker.gestureid.sensor.manager.SensorManager;

public class SensorManagerConfiguration {
    private static ISensorManager sensorManager = null;

    /**
     * Get instance of the custom Sensor Manager with the Hardware SensorManager.
     *
     * @return sensor manager instance.
     */
    public static ISensorManager getManager() {
        if (sensorManager == null) {
            sensorManager = new SensorManager();
        }
        return sensorManager;
    }

    /**
     * Rebuild and get instance of the custom Sensor Manager with the Hardware SensorManager.
     *
     * @return sensor manager instance.
     */
    public static ISensorManager getFreshManager() {
        sensorManager.destroy();
        sensorManager = new SensorManager();
        return sensorManager;
    }
}
