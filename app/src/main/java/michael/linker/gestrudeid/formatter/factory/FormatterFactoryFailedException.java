package michael.linker.gestrudeid.formatter.factory;

/**
 * If it is impossible to return any implementation due to an error
 */
public class FormatterFactoryFailedException extends RuntimeException {
    public FormatterFactoryFailedException(String message) {
        super(message);
    }
}
