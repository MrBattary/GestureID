package michael.linker.gestureid.analyzer.config.properties.exception;

public class PropertiesNotAvailableException extends RuntimeException {
    private static final String MSG = "The properties file is not available.";

    public PropertiesNotAvailableException(Throwable cause) {
        super(MSG, cause);
    }
}
