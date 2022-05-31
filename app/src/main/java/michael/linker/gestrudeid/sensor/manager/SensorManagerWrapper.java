package michael.linker.gestrudeid.sensor.manager;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.type.SensorType;

public class SensorManagerWrapper extends ASensorManager {
    private final SensorManager hardwareSensorManager;

    public SensorManagerWrapper(SensorManager hardwareSensorManager) {
        this.hardwareSensorManager = hardwareSensorManager;
    }

    @Override
    public void registerListener(SensorEventListener listener, Sensor sensor, Integer delay) {
        hardwareSensorManager.registerListener(listener, sensor, delay);
    }

    @Override
    public void unregisterListener(SensorEventListener listener) {
        hardwareSensorManager.unregisterListener(listener);
    }

    @Override
    public Sensor getDefaultSensor(SensorType sensorType) {
        return hardwareSensorManager.getDefaultSensor(sensorType.toInt());
    }
}
