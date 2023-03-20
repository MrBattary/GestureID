package michael.linker.gestureid.data.stream.output.file;

import michael.linker.gestureid.data.stream.output.OutputStreamException;

public class FileOutputStreamException extends OutputStreamException {
    public FileOutputStreamException(String message) {
        super(message);
    }

    public FileOutputStreamException(String message, Throwable cause) {
        super(message, cause);
    }
}
