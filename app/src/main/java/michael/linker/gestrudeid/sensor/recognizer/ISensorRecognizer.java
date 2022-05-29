package michael.linker.gestrudeid.sensor.recognizer;

import michael.linker.gestrudeid.sensor.model.ASensorModel;
import michael.linker.gestrudeid.sensor.type.SensorType;

public interface ISensorRecognizer {
    /**
     * Recognizes sensor by the provided ID
     *
     * @param sensorId ID for recognizing
     * @return SensorType
     * @throws SensorTypeRecognizerFailedException If it cannot be recognized by the provided ID
     */
    SensorType recognizeSensorById(Integer sensorId) throws SensorTypeRecognizerFailedException;

    
    ASensorModel recognizeSensorModelByType(SensorType sensorType)
            throws SensorTypeRecognizerFailedException;
}
