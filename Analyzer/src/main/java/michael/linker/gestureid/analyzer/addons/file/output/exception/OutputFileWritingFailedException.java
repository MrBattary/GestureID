package michael.linker.gestureid.analyzer.addons.file.output.exception;

import java.nio.file.Path;

public class OutputFileWritingFailedException extends RuntimeException {
    private static final String MSG = "File %s is not writable!";

    public OutputFileWritingFailedException(Path filePath, Throwable cause) {
        super(String.format(MSG, filePath.toString()), cause);
    }
}
