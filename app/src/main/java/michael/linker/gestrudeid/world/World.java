package michael.linker.gestrudeid.world;

import michael.linker.gestrudeid.sensor.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.provider.ISensorProvider;
import michael.linker.gestrudeid.sensor.provider.SensorProvider;

public class World implements IWorld {
    private final ASensorManager sensorManager;
    private final ISensorProvider sensorConfiguration;

    public World(final ASensorManager sensorManager) {
        this.sensorManager = sensorManager;
        sensorConfiguration = new SensorProvider(this.sensorManager);
    }
}
