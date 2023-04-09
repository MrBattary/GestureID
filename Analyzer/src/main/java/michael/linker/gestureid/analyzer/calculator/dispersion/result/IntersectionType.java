package michael.linker.gestureid.analyzer.calculator.dispersion.result;

public enum IntersectionType {
    NORMAL("normal"),
    FULL("full");

    private final String type;

    IntersectionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
