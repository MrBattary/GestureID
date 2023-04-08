package michael.linker.gestureid.analyzer.calculator;

public enum CalculatorType {
    INTERSECTION("intersection");

    private final String type;

    CalculatorType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
