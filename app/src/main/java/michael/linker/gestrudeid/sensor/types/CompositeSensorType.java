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
    final public static SensorType GRAVITY;
    /**
     * Activity sensor
     * Required sensors Accelerometer
     * Replaceable sensors Gyroscope
     * Alternative sensor: Magnetometer
     */
    final public static SensorType LINEAR_ACCELERATION;
    /**
     * Attitude sensor
     * Required sensors: Accelerometer, Magnetometer, Gyroscope
     */
    final public static SensorType ROTATION_VECTOR;
    /**
     * Simplified version of the ROTATION_VECTOR sensor
     * <p>
     * Attitude sensor
     * Required sensors: Accelerometer, Magnetometer
     */
    final public static SensorType GEOMAGNETIC_ROTATION_VECTOR;

    static {
        GRAVITY = new SensorType(Sensor.TYPE_GRAVITY);
        LINEAR_ACCELERATION = new SensorType(Sensor.TYPE_LINEAR_ACCELERATION);
        ROTATION_VECTOR = new SensorType(Sensor.TYPE_ROTATION_VECTOR);
        GEOMAGNETIC_ROTATION_VECTOR = new SensorType(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
    }

    protected final SensorType type;

    private CompositeSensorType(final int type) {
        this.type = new SensorType(type);
    }
}