package michael.linker.gestrudeid.stream.manager;

/**
 * If required output/input stream was not found
 */
public class StreamManagerNotFoundException extends RuntimeException {
    public StreamManagerNotFoundException(String message) {
        super(message);
    }

    public StreamManagerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
