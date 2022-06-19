package michael.linker.gestureid.sensor.recognizer;

/**
 * If there was any error related to the failure
 */
public class SensorTypeRecognizerFailedException extends RuntimeException {
    public SensorTypeRecognizerFailedException(String message) {
        super(message);
    }
}
