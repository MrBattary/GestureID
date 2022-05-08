package michael.linker.gestrudeid.streams.provider;

/**
 * If required output/input stream was not found
 */
public class StreamsProviderNotFoundException extends RuntimeException {
    public StreamsProviderNotFoundException(String message) {
        super(message);
    }

    public StreamsProviderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
