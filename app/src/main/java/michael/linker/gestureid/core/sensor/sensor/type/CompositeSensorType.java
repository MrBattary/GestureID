package michael.linker.gestureid.core.sensor.sensor.type;

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
    public final static SensorType GRAVITY;
    /**
     * Activity sensor
     * Required sensors Accelerometer
     * Replaceable sensors Gyroscope
     * Alternative sensor: Magnetometer
     */
    public final static SensorType LINEAR_ACCELERATION;
    /**
     * Attitude sensor
     * Required sensors: Accelerometer, Magnetometer, Gyroscope
     */
    public final static SensorType ROTATION_VECTOR;
    /**
     * Simplified version of the ROTATION_VECTOR sensor
     * <p>
     * Attitude sensor
     * Required sensors: Accelerometer, Magnetometer
     */
    public final static SensorType GEOMAGNETIC_ROTATION_VECTOR;

    static {
        GRAVITY = new SensorType(Sensor.TYPE_GRAVITY, "GRAVITY");
        LINEAR_ACCELERATION = new SensorType(Sensor.TYPE_LINEAR_ACCELERATION,
                "LINEAR_ACCELERATION");
        ROTATION_VECTOR = new SensorType(Sensor.TYPE_ROTATION_VECTOR, "ROTATION_VECTOR");
        GEOMAGNETIC_ROTATION_VECTOR = new SensorType(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR,
                "GEOMAGNETIC_ROTATION_VECTOR");
    }

    final SensorType type;

    private CompositeSensorType(final int typeInt, final String type) {
        this.type = new SensorType(typeInt, type);
    }
}