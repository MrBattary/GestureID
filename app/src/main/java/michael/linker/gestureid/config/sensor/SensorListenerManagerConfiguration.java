package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.BuildConfig;
import michael.linker.gestureid.config.IConfiguration;
import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorListenerManagerBean;
import michael.linker.gestureid.core.sensor.sensor.type.SensorDelayType;
import michael.linker.gestureid.data.sensor.listener.manager.ISensorListenerManager;
import michael.linker.gestureid.data.sensor.listener.manager.SensorListenerManager;

public final class SensorListenerManagerConfiguration implements IConfiguration {
    private static ConfigurationBean<ISensorListenerManager> sensorListenerManagerBean = null;

    public static ISensorListenerManager getSensorListenerManager() {
        if (sensorListenerManagerBean == null) {
            sensorListenerManagerBean = new SensorListenerManagerBean(new SensorListenerManager());
        }
        return (ISensorListenerManager) sensorListenerManagerBean;
    }

    @Override
    public void configure() {
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
