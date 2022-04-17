package michael.linker.gestrudeid.output.factory;

/**
 * If the factory cannot return the implementation due to an error
 */
public class SensorStreamFactoryFailedException extends RuntimeException {
    public SensorStreamFactoryFailedException(String message) {
        super(message);
    }
}
