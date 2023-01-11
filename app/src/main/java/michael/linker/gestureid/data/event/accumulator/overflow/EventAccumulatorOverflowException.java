package michael.linker.gestureid.data.event.accumulator.overflow;

/**
 * If there was buffer overflow.
 */
public class EventAccumulatorOverflowException extends RuntimeException {
    public EventAccumulatorOverflowException(String message) {
        super(message);
    }
}
