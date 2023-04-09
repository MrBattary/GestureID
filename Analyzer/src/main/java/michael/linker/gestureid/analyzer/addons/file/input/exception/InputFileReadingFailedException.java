package michael.linker.gestureid.analyzer.addons.file.input.exception;

import java.nio.file.Path;

public class InputFileReadingFailedException extends RuntimeException {
    private static final String MSG = "File %s cannot be read!";

    public InputFileReadingFailedException(Path filePath, Throwable cause) {
        super(String.format(MSG, filePath.toString()), cause);
    }
}
