package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.config.IConfiguration;
import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorManagerBean;
import michael.linker.gestureid.data.sensor.manager.ISensorManager;
import michael.linker.gestureid.data.sensor.manager.SensorManager;

public final class SensorManagerConfiguration implements IConfiguration {
    private static ConfigurationBean<ISensorManager> sensorManagerBean = null;

    /**
     * Get instance of the custom Sensor Manager with the Hardware SensorManager.
     *
     * @return sensor manager instance.
     */
    public static ISensorManager getManager() {
        if (sensorManagerBean == null) {
            sensorManagerBean = new SensorManagerBean(new SensorManager());
        }
        return (ISensorManager) sensorManagerBean;
    }

    @Override
    public void configure() {
        if (sensorManagerBean == null) {
            sensorManagerBean = new SensorManagerBean(new SensorManager());
        } else {
            ((ISensorManager) sensorManagerBean).destroy();
            sensorManagerBean.implement(new SensorManager());
        }
    }
}
