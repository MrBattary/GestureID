package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.BuildConfig;
import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorListenerManagerBean;
import michael.linker.gestureid.core.sensor.sensor.type.SensorDelayType;
import michael.linker.gestureid.data.sensor.listener.manager.ISensorListenerManager;
import michael.linker.gestureid.data.sensor.listener.manager.SensorListenerManager;

public final class SensorListenerManagerConfiguration {
    private static ConfigurationBean<ISensorListenerManager> sensorListenerManagerBean;

    public static ISensorListenerManager getSensorListenerManager() {
        if (sensorListenerManagerBean == null) {
            sensorListenerManagerBean = new SensorListenerManagerBean(new SensorListenerManager());
        }
        return (ISensorListenerManager) sensorListenerManagerBean;
    }

    public static void configure() {
        if (sensorListenerManagerBean == null) {
            sensorListenerManagerBean = new SensorListenerManagerBean(new SensorListenerManager());
        } else {
            sensorListenerManagerBean.implement(new SensorListenerManager());
        }
    }

    public static class Build {
        public static SensorDelayType getSensorDelay() {
            return new SensorDelayType(BuildConfig.SENSOR_DELAY);
        }
    }
}
