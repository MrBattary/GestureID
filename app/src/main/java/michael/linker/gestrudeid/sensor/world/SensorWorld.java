package michael.linker.gestrudeid.sensor.world;

import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.provider.ISensorsProvider;
import michael.linker.gestrudeid.sensor.provider.SensorsProvider;

public class SensorWorld implements ISensorWorld {
    private final SensorManager sensorManager;
    private final ISensorsProvider sensorConfiguration;

    public SensorWorld(final SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        sensorConfiguration = new SensorsProvider(this.sensorManager);
    }
}
