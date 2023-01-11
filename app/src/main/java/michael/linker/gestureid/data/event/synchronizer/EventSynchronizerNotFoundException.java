package michael.linker.gestureid.data.event.synchronizer;

/**
 * If there was any error related to the lack of the necessary resource
 */
public class EventSynchronizerNotFoundException extends RuntimeException {
    public EventSynchronizerNotFoundException(String message) {
        super(message);
    }
}
