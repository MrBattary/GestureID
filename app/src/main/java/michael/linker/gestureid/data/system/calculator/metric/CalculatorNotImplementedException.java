package michael.linker.gestureid.data.system.calculator.metric;

public class CalculatorNotImplementedException extends RuntimeException {
    private static final String MSG = "Calculator not implemented!";

    public CalculatorNotImplementedException() {
        super(MSG);
    }
}
