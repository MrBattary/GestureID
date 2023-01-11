package michael.linker.gestureid.data.sensor.listener.manager;

/**
 * If there was any error related to the failure
 */
public class SensorListenerManagerFailedException extends RuntimeException {
    public SensorListenerManagerFailedException(String message) {
        super(message);
    }

    public SensorListenerManagerFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
