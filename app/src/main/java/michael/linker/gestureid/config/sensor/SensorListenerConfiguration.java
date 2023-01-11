package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.BuildConfig;
import michael.linker.gestureid.data.sensor.listener.manager.ISensorListenerManager;
import michael.linker.gestureid.data.sensor.listener.manager.SensorListenerManager;
import michael.linker.gestureid.data.sensor.listener.provider.ISensorListenerProvider;
import michael.linker.gestureid.data.sensor.listener.provider.SensorListenerProvider;
import michael.linker.gestureid.data.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.data.sensor.listener.suppressor.SensorListenerSuppressor;
import michael.linker.gestureid.core.sensor.sensor.type.SensorDelayType;

public final class SensorListenerConfiguration {
    private static ISensorListenerSuppressor sensorListenerSuppressor;
    private static ISensorListenerProvider sensorListenerProvider;
    private static ISensorListenerManager sensorListenerManager;

    public static ISensorListenerSuppressor getSensorListenerSuppressor() {
        if (sensorListenerSuppressor == null) {
            sensorListenerSuppressor = new SensorListenerSuppressor();
        }
        return sensorListenerSuppressor;
    }

    public static ISensorListenerProvider getSensorListenerProvider() {
        if (sensorListenerProvider == null) {
            sensorListenerProvider = new SensorListenerProvider();
        }
        return sensorListenerProvider;
    }

    public static ISensorListenerManager getSensorListenerManager() {
        if (sensorListenerManager == null) {
            sensorListenerManager = new SensorListenerManager();
        }
        return sensorListenerManager;
    }

    public static class Build {
        public static SensorDelayType getSensorDelay() {
            return new SensorDelayType(BuildConfig.SENSOR_DELAY);
        }
    }
}
