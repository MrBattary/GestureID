package michael.linker.gestureid.analyzer.manager;

public class AnalysisManagerFailedException extends RuntimeException {
    private static final String MSG = "The analysis is failed.";

    public AnalysisManagerFailedException(Throwable cause) {
        super(MSG, cause);
    }
}
