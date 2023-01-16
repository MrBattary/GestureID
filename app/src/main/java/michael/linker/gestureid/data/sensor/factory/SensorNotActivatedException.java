package michael.linker.gestureid.data.sensor.factory;

/**
 * If sensor was disabled in the configuration file
 */
public class SensorNotActivatedException extends RuntimeException {
    public SensorNotActivatedException(String message) {
        super(message);
    }
}
