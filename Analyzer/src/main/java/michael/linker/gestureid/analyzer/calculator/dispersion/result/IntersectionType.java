package michael.linker.gestureid.analyzer.calculator.dispersion.result;

import michael.linker.gestureid.analyzer.config.CalculationsConfiguration;

public enum IntersectionType {
    FIRST_MATCH(CalculationsConfiguration.getDispersionModeFirstMatch()),
    FULL(CalculationsConfiguration.getDispersionModeFull());

    private final String type;

    IntersectionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
