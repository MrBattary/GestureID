package michael.linker.gestureid.event.buffer.overflow;

/**
 * If there was buffer overflow.
 */
public class EventBufferOverflowException extends RuntimeException {
    public EventBufferOverflowException(String message) {
        super(message);
    }
}
