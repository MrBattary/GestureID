package michael.linker.gestrudeid.sensor.world;

import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.config.ISensorConfiguration;
import michael.linker.gestrudeid.sensor.config.SensorConfiguration;

public class SensorWorld implements ISensorWorld {
    private final SensorManager sensorManager;
    private final ISensorConfiguration sensorConfiguration;

    public SensorWorld(final SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        sensorConfiguration = new SensorConfiguration(this.sensorManager);
    }
}
