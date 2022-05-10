package michael.linker.gestrudeid.sensor.synchronizer;

/**
 * If there was any error related to the lack of the necessary resource
 */
public class SensorEventSynchronizerNotFoundException extends RuntimeException {
    public SensorEventSynchronizerNotFoundException(String message) {
        super(message);
    }
}
