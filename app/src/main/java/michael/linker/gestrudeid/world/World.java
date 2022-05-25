package michael.linker.gestrudeid.world;

import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.provider.ISensorProvider;
import michael.linker.gestrudeid.sensor.provider.SensorProvider;

public class World implements IWorld {
    private final SensorManager sensorManager;
    private final ISensorProvider sensorConfiguration;

    public World(final SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        sensorConfiguration = new SensorProvider(this.sensorManager);
    }
}
