package michael.linker.gestureid.data.sensor.manager.exception;

public class SensorManagerFailedException extends RuntimeException {
    public SensorManagerFailedException(String message) {
        super(message);
    }

    public SensorManagerFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
