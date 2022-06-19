package michael.linker.gestureid.sensor.wrapper.sensor;

import android.hardware.Sensor;

import michael.linker.gestureid.sensor.type.SensorType;

/**
 * A wrapper around hardware Sensor
 */
public class SensorWrapper {
    private final SensorType sensorType;
    private final Sensor hardwareSensor;

    public SensorWrapper(SensorType sensorType, Sensor hardwareSensor) {
        this.sensorType = sensorType;
        this.hardwareSensor = hardwareSensor;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public Sensor getHardwareSensor() {
        return hardwareSensor;
    }
}
