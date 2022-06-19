package michael.linker.gestureid.sensor.listener.suppressor;

/**
 * If the listener was not found
 */
public class SensorListenerSuppressorNotFoundException extends RuntimeException {
    public SensorListenerSuppressorNotFoundException(String message) {
        super(message);
    }
}
