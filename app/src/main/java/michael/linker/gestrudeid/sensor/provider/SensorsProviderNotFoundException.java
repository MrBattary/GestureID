package michael.linker.gestrudeid.sensor.provider;

/**
 * If required sensor was not found
 */
public class SensorsProviderNotFoundException extends RuntimeException {

    public SensorsProviderNotFoundException(String message) {
        super(message);
    }

    public SensorsProviderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
