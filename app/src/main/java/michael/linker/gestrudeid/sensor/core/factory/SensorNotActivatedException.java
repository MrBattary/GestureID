package michael.linker.gestrudeid.sensor.core.factory;

/**
 * If sensor was disabled in the configuration file
 */
public class SensorNotActivatedException extends RuntimeException {
    public SensorNotActivatedException(String message) {
        super(message);
    }
}
