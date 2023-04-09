package michael.linker.gestureid.analyzer.addons.file.exception;

import java.nio.file.Path;

public class FileCreationFaultException extends RuntimeException {
    public static final String MSG = "An error occurred while creating file %s.";

    public FileCreationFaultException(Path filePath) {
        super(String.format(MSG, filePath.toString()));
    }
}
