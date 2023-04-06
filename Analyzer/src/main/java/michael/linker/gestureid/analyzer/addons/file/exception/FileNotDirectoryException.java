package michael.linker.gestureid.analyzer.addons.file.exception;

import java.nio.file.Path;

public class FileNotDirectoryException extends RuntimeException {
    public static final String MSG = "The object located with path %s is not a directory!";

    public FileNotDirectoryException(Path directoryPath) {
        super(String.format(MSG, directoryPath));
    }
}
