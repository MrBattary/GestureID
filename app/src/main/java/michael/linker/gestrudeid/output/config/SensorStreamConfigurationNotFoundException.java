package michael.linker.gestrudeid.output.config;

/**
 * If required output stream was not found
 */
public class SensorStreamConfigurationNotFoundException extends RuntimeException {
    public SensorStreamConfigurationNotFoundException(String message) {
        super(message);
    }

    public SensorStreamConfigurationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
