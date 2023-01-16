package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.config.IConfiguration;
import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorListenerProviderBean;
import michael.linker.gestureid.data.sensor.listener.provider.ISensorListenerProvider;
import michael.linker.gestureid.data.sensor.listener.provider.SensorListenerProvider;

public final class SensorListenerProviderConfiguration implements IConfiguration {
    private static ConfigurationBean<ISensorListenerProvider> sensorListenerProviderBean = null;

    public static ISensorListenerProvider getSensorListenerProvider() {
        if (sensorListenerProviderBean == null) {
            sensorListenerProviderBean =
                    new SensorListenerProviderBean(new SensorListenerProvider());
        }
        return (ISensorListenerProvider) sensorListenerProviderBean;
    }

    @Override
    public void configure() {
        if (sensorListenerProviderBean == null) {
            sensorListenerProviderBean =
                    new SensorListenerProviderBean(new SensorListenerProvider());
        } else {
            sensorListenerProviderBean.implement(new SensorListenerProvider());
        }
    }
}
