package michael.linker.gestrudeid.stream.manager;

/**
 * If it is impossible to return any implementation due to an error
 */
public class StreamManagerFailedException extends RuntimeException {
    public StreamManagerFailedException(String message) {
        super(message);
    }
}
