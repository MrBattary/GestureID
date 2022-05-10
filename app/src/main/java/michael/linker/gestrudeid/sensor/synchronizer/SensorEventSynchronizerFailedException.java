package michael.linker.gestrudeid.sensor.synchronizer;

/**
 * If there was any error related to the failure
 */
public class SensorEventSynchronizerFailedException extends RuntimeException {
    public SensorEventSynchronizerFailedException(String message) {
        super(message);
    }
}
