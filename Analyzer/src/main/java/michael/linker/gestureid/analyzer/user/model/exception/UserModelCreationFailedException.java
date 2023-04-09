package michael.linker.gestureid.analyzer.user.model.exception;

import java.nio.file.Path;

public class UserModelCreationFailedException extends RuntimeException {
    private static final String MSG = "Failed to create a user model from file %s!";

    public UserModelCreationFailedException(Path modelPath, Throwable cause) {
        super(String.format(MSG, modelPath.toString()), cause);
    }
}
