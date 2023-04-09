package michael.linker.gestureid.analyzer.calculator.addons.intersection.model;

import michael.linker.gestureid.analyzer.config.CalculationsConfiguration;

public enum IntersectionType {
    FIRST_MATCH(CalculationsConfiguration.getIntersectorModeFirstMatch()),
    FULL(CalculationsConfiguration.getIntersectorModeFull());

    private final String type;

    IntersectionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
