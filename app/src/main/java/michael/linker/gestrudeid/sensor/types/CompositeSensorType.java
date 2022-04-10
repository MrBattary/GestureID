package michael.linker.gestrudeid.sensor.types;

import android.hardware.Sensor;

/**
 * A composite sensor generates data by processing and/or fusing data from one or several
 * physical BASE sensors.
 * Any sensor that isn't a base sensor is called a composite sensor.
 *
 * @link https://source.android.com/devices/sensors/sensor-types#composite_sensor_types
 */
public final class CompositeSensorType {
    /**
     * Attitude sensor
     * Required sensors Accelerometer
     * Replaceable sensors Gyroscope
     * Alternative sensor: Magnetometer
     */
    final public static int GRAVITY;
    /**
     * Activity sensor
     * Required sensors Accelerometer
     * Replaceable sensors Gyroscope
     * Alternative sensor: Magnetometer
     */
    final public static int LINEAR_ACCELERATION;
    /**
     * Attitude sensor
     * Required sensors: Accelerometer, Magnetometer, Gyroscope
     */
    final public static int ROTATION_VECTOR;
    /**
     * Simplified version of the ROTATION_VECTOR sensor
     *
     * Attitude sensor
     * Required sensors: Accelerometer, Magnetometer
     */
    final public static int GEOMAGNETIC_ROTATION_VECTOR;

    static {
        GRAVITY = Sensor.TYPE_GRAVITY;
        LINEAR_ACCELERATION = Sensor.TYPE_LINEAR_ACCELERATION;
        ROTATION_VECTOR = Sensor.TYPE_ROTATION_VECTOR;
        GEOMAGNETIC_ROTATION_VECTOR = Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR;
    }

    protected final int type;

    private CompositeSensorType(final int type) {
        this.type = type;
    }
}