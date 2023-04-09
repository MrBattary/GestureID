package michael.linker.gestureid.analyzer.calculator;

public enum CalculatorType {
    INTERSECTION("intersection"),
    AMOUNT("amount");

    private final String type;

    CalculatorType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
