package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.data.sensor.provider.ISensorProvider;
import michael.linker.gestureid.data.sensor.provider.SensorProvider;

public final class SensorProviderConfiguration {
    private static ISensorProvider sensorProvider = null;

    public static ISensorProvider getSensorProvider() {
        if (sensorProvider == null) {
            sensorProvider = new SensorProvider();
        }
        return sensorProvider;
    }
}
