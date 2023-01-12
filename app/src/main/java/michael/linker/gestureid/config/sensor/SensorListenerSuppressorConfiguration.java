package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorListenerSuppressorBean;
import michael.linker.gestureid.data.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.data.sensor.listener.suppressor.SensorListenerSuppressor;

public final class SensorListenerSuppressorConfiguration {
    private static ConfigurationBean<ISensorListenerSuppressor> sensorListenerSuppressorBean;

    public static ISensorListenerSuppressor getSensorListenerSuppressor() {
        if (sensorListenerSuppressorBean == null) {
            sensorListenerSuppressorBean =
                    new SensorListenerSuppressorBean(new SensorListenerSuppressor());
        }
        return (ISensorListenerSuppressor) sensorListenerSuppressorBean;
    }

    public static void configure() {
        if (sensorListenerSuppressorBean == null) {
            sensorListenerSuppressorBean =
                    new SensorListenerSuppressorBean(new SensorListenerSuppressor());
        } else {
            sensorListenerSuppressorBean.implement(new SensorListenerSuppressor());
        }
    }
}
