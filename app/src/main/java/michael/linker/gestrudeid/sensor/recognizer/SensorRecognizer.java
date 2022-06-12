package michael.linker.gestrudeid.sensor.recognizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import michael.linker.gestrudeid.sensor.model.ASensorModel;
import michael.linker.gestrudeid.sensor.model.base.AccelerometerSensorModel;
import michael.linker.gestrudeid.sensor.model.base.GyroscopeSensorModel;
import michael.linker.gestrudeid.sensor.model.base.MagneticFieldSensorModel;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;
import michael.linker.gestrudeid.sensor.type.SensorType;

/**
 * Utility class that helps connect sensor types
 */
public class SensorRecognizer {
    private static final Map<Integer, SensorType> SENSOR_TYPE_MAP;
    private static final Map<Integer, ASensorModel> TYPE_SENSOR_MODEL_MAP;

    static {
        Map<Integer, SensorType> unmodifiableTypeMap = new HashMap<>();
        // Base types
        unmodifiableTypeMap.put(BaseSensorType.ACCELEROMETER.toInt(),
                BaseSensorType.ACCELEROMETER);
        unmodifiableTypeMap.put(BaseSensorType.GYROSCOPE.toInt(),
                BaseSensorType.GYROSCOPE);
        unmodifiableTypeMap.put(BaseSensorType.MAGNETOMETER.toInt(),
                BaseSensorType.MAGNETOMETER);
        // Composite types
        unmodifiableTypeMap.put(CompositeSensorType.ROTATION_VECTOR .toInt(),
                CompositeSensorType.ROTATION_VECTOR);
        unmodifiableTypeMap.put(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR.toInt(),
                CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR);
        unmodifiableTypeMap.put(CompositeSensorType.GRAVITY.toInt(),
                CompositeSensorType.GRAVITY);
        unmodifiableTypeMap.put(CompositeSensorType.LINEAR_ACCELERATION.toInt(),
                CompositeSensorType.LINEAR_ACCELERATION);
        SENSOR_TYPE_MAP = Collections.unmodifiableMap(unmodifiableTypeMap);

        // Models
        Map<Integer, ASensorModel> unmodifiableModelMap = new HashMap<>();
        unmodifiableModelMap.put(BaseSensorType.ACCELEROMETER.toInt(),
                new AccelerometerSensorModel());
        unmodifiableModelMap.put(BaseSensorType.GYROSCOPE.toInt(),
                new GyroscopeSensorModel());
        unmodifiableModelMap.put(BaseSensorType.MAGNETOMETER.toInt(),
                new MagneticFieldSensorModel());
        TYPE_SENSOR_MODEL_MAP = Collections.unmodifiableMap(unmodifiableModelMap);

    }

    /**
     * Recognizes sensor by the provided ID
     *
     * @param typeId ID for recognizing
     * @return SensorType
     * @throws SensorTypeRecognizerFailedException If it cannot be recognized by the provided ID
     */
    public static SensorType recognizeSensorById(final Integer typeId)
            throws SensorTypeRecognizerFailedException {
        SensorType sensorType = SENSOR_TYPE_MAP.get(typeId);
        if(sensorType == null) {
            throw new SensorTypeRecognizerFailedException("Sensor type can't be recognized!");
        }
        return sensorType;
    }

    /**
     * Recognizes sensor model by the provided sensor type
     *
     * @param sensorType Sensor type for recognizing
     * @return SensorModel
     * @throws SensorTypeRecognizerFailedException If it cannot be recognized by the provided type
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
