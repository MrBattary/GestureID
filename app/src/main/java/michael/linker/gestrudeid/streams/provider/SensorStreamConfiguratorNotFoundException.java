package michael.linker.gestrudeid.streams.provider;

/**
 * If required output/input stream was not found
 */
public class SensorStreamConfiguratorNotFoundException extends RuntimeException {
    public SensorStreamConfiguratorNotFoundException(String message) {
        super(message);
    }

    public SensorStreamConfiguratorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
