package michael.linker.gestureid.analyzer.calculator;

public class CalculatorFailedException extends RuntimeException {
    private static final String MSG = "The calculation is failed.";

    public CalculatorFailedException(Throwable cause) {
        super(MSG, cause);
    }
}
