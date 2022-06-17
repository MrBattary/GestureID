package michael.linker.gestrudeid.world.exception;

public class WorldFailedException extends RuntimeException {
    public WorldFailedException(String message) {
        super(message);
    }

    public WorldFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
