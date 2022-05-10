package michael.linker.gestrudeid.sensor.core.factory;

/**
 * If sensor was not found
 */
public class SensorNotFoundException extends RuntimeException {
    public SensorNotFoundException(final String message) {
        super(message);
    }

    public SensorNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
