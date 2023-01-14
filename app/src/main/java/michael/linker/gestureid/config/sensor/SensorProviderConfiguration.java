package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.config.IConfiguration;
import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorProviderBean;
import michael.linker.gestureid.data.sensor.provider.ISensorProvider;
import michael.linker.gestureid.data.sensor.provider.SensorProvider;

public final class SensorProviderConfiguration implements IConfiguration {
    private static ConfigurationBean<ISensorProvider> sensorProviderBean = null;

    public static ISensorProvider getSensorProvider() {
        if (sensorProviderBean == null) {
            sensorProviderBean = new SensorProviderBean(new SensorProvider());
        }
        return (ISensorProvider) sensorProviderBean;
    }

    @Override
    public void configure() {
        if (sensorProviderBean == null) {
            sensorProviderBean = new SensorProviderBean(new SensorProvider());
        } else {
            sensorProviderBean.implement(new SensorProvider());
        }
    }
}
