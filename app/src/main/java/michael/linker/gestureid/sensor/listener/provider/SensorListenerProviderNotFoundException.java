package michael.linker.gestureid.sensor.listener.provider;

/**
 * If required sensor listener was not found
 */
public class SensorListenerProviderNotFoundException extends RuntimeException {
    public SensorListenerProviderNotFoundException(String message) {
        super(message);
    }
}
