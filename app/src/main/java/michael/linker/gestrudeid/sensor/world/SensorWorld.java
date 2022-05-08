package michael.linker.gestrudeid.sensor.world;

import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.provider.ISensorProvider;
import michael.linker.gestrudeid.sensor.provider.SensorProvider;

public class SensorWorld implements ISensorWorld {
    private final SensorManager sensorManager;
    private final ISensorProvider sensorConfiguration;

    public SensorWorld(final SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        sensorConfiguration = new SensorProvider(this.sensorManager);
    }
}
