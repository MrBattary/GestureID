package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorProviderBean;
import michael.linker.gestureid.data.sensor.provider.ISensorProvider;
import michael.linker.gestureid.data.sensor.provider.SensorProvider;

public final class SensorProviderConfiguration {
    private static ConfigurationBean<ISensorProvider> sensorProviderBean = null;

    public static ISensorProvider getSensorProvider() {
        if (sensorProviderBean == null) {
            sensorProviderBean = new SensorProviderBean(new SensorProvider());
        }
        return (ISensorProvider) sensorProviderBean;
    }

    public static void configure() {
        if (sensorProviderBean == null) {
            sensorProviderBean = new SensorProviderBean(new SensorProvider());
        } else {
            sensorProviderBean.implement(new SensorProvider());
        }
    }
}
