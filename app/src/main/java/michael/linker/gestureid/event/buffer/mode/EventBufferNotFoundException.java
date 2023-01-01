package michael.linker.gestureid.event.buffer.mode;

/**
 * If there was any error related to the lack of the necessary resource
 */
public class EventBufferNotFoundException extends RuntimeException {
    public EventBufferNotFoundException(String message) {
        super(message);
    }
}
