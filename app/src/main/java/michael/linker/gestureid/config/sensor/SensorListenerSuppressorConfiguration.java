package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.config.IConfiguration;
import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorListenerSuppressorBean;
import michael.linker.gestureid.data.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.data.sensor.listener.suppressor.SensorListenerSuppressor;

public final class SensorListenerSuppressorConfiguration implements IConfiguration {
    private static ConfigurationBean<ISensorListenerSuppressor> sensorListenerSuppressorBean = null;

    public static ISensorListenerSuppressor getSensorListenerSuppressor() {
        if (sensorListenerSuppressorBean == null) {
            sensorListenerSuppressorBean =
                    new SensorListenerSuppressorBean(new SensorListenerSuppressor());
        }
        return (ISensorListenerSuppressor) sensorListenerSuppressorBean;
    }

    @Override
    public void configure() {
        if (sensorListenerSuppressorBean == null) {
            sensorListenerSuppressorBean =
                    new SensorListenerSuppressorBean(new SensorListenerSuppressor());
        } else {
            sensorListenerSuppressorBean.implement(new SensorListenerSuppressor());
        }
    }
}
