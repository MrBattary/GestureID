package michael.linker.gestureid.data.sensor.recognizer;

import java.util.Map;

import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.data.sensor.model.ASensorModel;
import michael.linker.gestureid.data.sensor.model.base.AccelerometerSensorModel;
import michael.linker.gestureid.data.sensor.model.base.GyroscopeSensorModel;
import michael.linker.gestureid.data.sensor.model.base.MagneticFieldSensorModel;

/**
 * Utility class that helps connect sensor types
 */
public class SensorRecognizer {
    private static final Map<Integer, SensorType> SENSOR_TYPE_MAP;
    private static final Map<Integer, ASensorModel> TYPE_SENSOR_MODEL_MAP;

    static {
        SENSOR_TYPE_MAP = Map.of(
                // Base types
                BaseSensorType.ACCELEROMETER.toInt(), BaseSensorType.ACCELEROMETER,
                BaseSensorType.GYROSCOPE.toInt(), BaseSensorType.GYROSCOPE,
                BaseSensorType.MAGNETOMETER.toInt(), BaseSensorType.MAGNETOMETER,
                // Composite types
                CompositeSensorType.ROTATION_VECTOR.toInt(), CompositeSensorType.ROTATION_VECTOR,
                CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR.toInt(), CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR,
                CompositeSensorType.GRAVITY.toInt(), CompositeSensorType.GRAVITY,
                CompositeSensorType.LINEAR_ACCELERATION.toInt(), CompositeSensorType.LINEAR_ACCELERATION);

        // Models
        TYPE_SENSOR_MODEL_MAP = Map.of(
                BaseSensorType.ACCELEROMETER.toInt(), new AccelerometerSensorModel(),
                BaseSensorType.GYROSCOPE.toInt(), new GyroscopeSensorModel(),
                BaseSensorType.MAGNETOMETER.toInt(), new MagneticFieldSensorModel());

    }

    /**
     * Recognizes sensor by the provided sensor ID.
     *
     * @param typeId ID for recognizing.
     * @return SensorType object.
     * @throws SensorTypeRecognizerFailedException If it cannot be recognized by the provided ID.
     */
    public static SensorType recognizeSensorById(final Integer typeId)
            throws SensorTypeRecognizerFailedException {
        SensorType sensorType = SENSOR_TYPE_MAP.get(typeId);
        if (sensorType == null) {
            throw new SensorTypeRecognizerFailedException("Sensor type can't be recognized!");
        }
        return sensorType;
    }

    /**
     * Recognizes sensor model by the provided sensor type.
     *
     * @param sensorType Sensor type for recognizing.
     * @return SensorModel object.
     * @throws SensorTypeRecognizerFailedException If it cannot be recognized by the provided type.
     */
    public static ASensorModel recognizeSensorModelByType(final SensorType sensorType)
            throws SensorTypeRecognizerFailedException {
        ASensorModel recognizedModel = TYPE_SENSOR_MODEL_MAP.get(sensorType.toInt());
        if (recognizedModel == null) {
            throw new SensorTypeRecognizerFailedException("Sensor model can't be recognized!");
        }
        return recognizedModel;
    }
}
