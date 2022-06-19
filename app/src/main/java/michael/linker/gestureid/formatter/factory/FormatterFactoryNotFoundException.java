package michael.linker.gestureid.formatter.factory;

/**
 * If required formatter was not found
 */
public class FormatterFactoryNotFoundException extends RuntimeException {
    public FormatterFactoryNotFoundException(String message) {
        super(message);
    }
}
