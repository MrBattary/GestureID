package michael.linker.gestureid.data.event.accumulator.mode;

/**
 * If there was any error related to the lack of the necessary resource
 */
public class EventAccumulatorNotFoundException extends RuntimeException {
    public EventAccumulatorNotFoundException(String message) {
        super(message);
    }
}
