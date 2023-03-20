package michael.linker.gestureid.data.stream.output;

import michael.linker.gestureid.data.stream.StreamException;

public class OutputStreamException extends StreamException {
    public OutputStreamException(String message) {
        super(message);
    }

    public OutputStreamException(String message, Throwable cause) {
        super(message, cause);
    }
}
