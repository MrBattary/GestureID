package michael.linker.gestrudeid.sensor.types;

import android.hardware.Sensor;

/**
 * Base sensor types are named after the physical sensors they represent.
 * These sensors relay data from a single physical sensor
 * (as opposed to composite sensors that generate data out of other sensors).
 *
 * @link https://source.android.com/devices/sensors/sensor-types#base_sensors
 */
public final class BaseSensorType {
    final public static int ACCELEROMETER;
    final public static int GYROSCOPE;
    final public static int MAGNETOMETER;

    static {
        ACCELEROMETER = Sensor.TYPE_ACCELEROMETER;
        GYROSCOPE = Sensor.TYPE_GYROSCOPE;
        MAGNETOMETER = Sensor.TYPE_MAGNETIC_FIELD;
    }

    final int type;

    private BaseSensorType(final int type) {
        this.type = type;
    }
}
