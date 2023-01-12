package michael.linker.gestureid.config.sensor;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.config.sensor.bean.SensorManagerBean;
import michael.linker.gestureid.data.sensor.manager.ISensorManager;
import michael.linker.gestureid.data.sensor.manager.SensorManager;

public class SensorManagerConfiguration {
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

    public static void configure() {
        if (sensorManagerBean == null) {
            sensorManagerBean = new SensorManagerBean(new SensorManager());
        } else {
            sensorManagerBean.getImplementation().destroy();
            sensorManagerBean.implement(new SensorManager());
        }
    }
}
