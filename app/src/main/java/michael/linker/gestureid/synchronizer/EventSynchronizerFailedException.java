package michael.linker.gestureid.synchronizer;

/**
 * If there was any error related to the failure
 */
public class EventSynchronizerFailedException extends RuntimeException {
    public EventSynchronizerFailedException(String message) {
        super(message);
    }
}
