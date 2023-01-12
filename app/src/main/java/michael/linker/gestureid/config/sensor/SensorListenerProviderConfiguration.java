package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorListenerProviderBean;
import michael.linker.gestureid.data.sensor.listener.provider.ISensorListenerProvider;
import michael.linker.gestureid.data.sensor.listener.provider.SensorListenerProvider;

public final class SensorListenerProviderConfiguration {
    private static ConfigurationBean<ISensorListenerProvider> sensorListenerProviderBean;

    public static ISensorListenerProvider getSensorListenerProvider() {
        if (sensorListenerProviderBean == null) {
            sensorListenerProviderBean =
                    new SensorListenerProviderBean(new SensorListenerProvider());
        }
        return (ISensorListenerProvider) sensorListenerProviderBean;
    }

    public static void configure() {
        if (sensorListenerProviderBean == null) {
            sensorListenerProviderBean =
                    new SensorListenerProviderBean(new SensorListenerProvider());
        } else {
            sensorListenerProviderBean.implement(new SensorListenerProvider());
        }
    }
}
