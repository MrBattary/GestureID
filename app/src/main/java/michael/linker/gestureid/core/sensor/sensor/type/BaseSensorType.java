package michael.linker.gestureid.core.sensor.sensor.type;

import android.hardware.Sensor;

/**
 * Base sensor types are named after the physical sensors they represent.
 * These sensors relay data from a single physical sensor
 * (as opposed to composite sensors that generate data out of other sensors).
 *
 * @link https://source.android.com/devices/sensors/sensor-types#base_sensors
 */
public final class BaseSensorType {
    public final static SensorType ACCELEROMETER;
    public final static SensorType GYROSCOPE;
    public final static SensorType MAGNETOMETER;

    static {
        ACCELEROMETER = new SensorType(Sensor.TYPE_ACCELEROMETER, "ACCELEROMETER");
        GYROSCOPE = new SensorType(Sensor.TYPE_GYROSCOPE, "GYROSCOPE");
        MAGNETOMETER = new SensorType(Sensor.TYPE_MAGNETIC_FIELD, "MAGNETOMETER");
    }

    final SensorType type;

    private BaseSensorType(final int typeInt, final String type) {
        this.type = new SensorType(typeInt, type);
    }
}
