package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.sensor.manager.ISensorManager;
import michael.linker.gestureid.sensor.manager.SensorManager;

public class SensorWorldConfiguration {
    private static ISensorManager world = null;

    /**
     * Get instance of the World with the hardware SensorManager.
     *
     * @return World instance.
     */
    public static ISensorManager getWorld() {
        if (world == null) {
            world = new SensorManager();
        }
        return world;
    }
}
