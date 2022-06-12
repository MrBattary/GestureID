package michael.linker.gestrudeid.sensor.wrapper.manager;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.type.SensorType;

/**
 * Wrapper for the hardware SensorManager
 *
 * @see android.hardware.SensorManager
 */
public abstract class ASensorManager {
    // Constants
    public static final int SENSOR_DELAY_FASTEST = SensorManager.SENSOR_DELAY_FASTEST;
    public static final int SENSOR_DELAY_NORMAL = SensorManager.SENSOR_DELAY_NORMAL;
    public static final int SENSOR_DELAY_UI = SensorManager.SENSOR_DELAY_UI;

    // Methods
    /**
     * @see android.hardware.SensorManager#registerListener(SensorEventListener, Sensor, int) 
     */
    public abstract void registerListener(SensorEventListener listener, Sensor sensor, Integer delay);

    /**
     * @see android.hardware.SensorManager#unregisterListener(SensorEventListener) 
     */
    public abstract void unregisterListener(SensorEventListener listener);

    /**
     * @see android.hardware.SensorManager#getDefaultSensor(int) 
     */
    public abstract Sensor getDefaultSensor(SensorType sensorType);
}
