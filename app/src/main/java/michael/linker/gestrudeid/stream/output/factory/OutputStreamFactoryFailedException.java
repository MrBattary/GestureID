package michael.linker.gestrudeid.stream.output.factory;

/**
 * If the factory cannot return the implementation due to an error
 */
public class OutputStreamFactoryFailedException extends RuntimeException {
    public OutputStreamFactoryFailedException(String message) {
        super(message);
    }
}
