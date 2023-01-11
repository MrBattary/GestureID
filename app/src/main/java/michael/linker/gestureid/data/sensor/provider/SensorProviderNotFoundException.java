package michael.linker.gestureid.data.sensor.provider;

/**
 * If required sensor was not found
 */
public class SensorProviderNotFoundException extends RuntimeException {
    public SensorProviderNotFoundException(String message) {
        super(message);
    }

    public SensorProviderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
