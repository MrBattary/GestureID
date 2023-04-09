package michael.linker.gestureid.analyzer.calculator;

import michael.linker.gestureid.analyzer.config.CalculationsConfiguration;

public enum CalculatorType {
    INTERSECTION(CalculationsConfiguration.getDispersionType()),
    FAR(CalculationsConfiguration.getFarType()),
    AMOUNT(CalculationsConfiguration.getAmountType());

    private final String type;

    CalculatorType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
