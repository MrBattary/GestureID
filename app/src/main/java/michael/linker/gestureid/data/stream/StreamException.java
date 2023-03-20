package michael.linker.gestureid.data.stream;

public class StreamException extends RuntimeException {
    public StreamException(String message) {
        super(message);
    }

    public StreamException(String message, Throwable cause) {
        super(message, cause);
    }
}
