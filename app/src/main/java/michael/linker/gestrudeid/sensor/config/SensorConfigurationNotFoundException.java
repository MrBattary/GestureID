package michael.linker.gestrudeid.sensor.config;

/**
 * If required sensor was not found
 */
public class SensorConfigurationNotFoundException extends RuntimeException {

    public SensorConfigurationNotFoundException(String message) {
        super(message);
    }

    public SensorConfigurationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
