package michael.linker.gestureid.analyzer.calculator.dispersion.result;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;

public abstract class DispersionCalculationTable {
    private final Double dispersion;
    private final ISquareTable<String, Integer> table;

    public DispersionCalculationTable(Double dispersion, ISquareTable<String, Integer> table) {
        this.dispersion = dispersion;
        this.table = table;
    }

    public Double dispersion() {
        return dispersion;
    }

    public ISquareTable<String, Integer> table() {
        return table;
    }

    public abstract DispersionIntersectionType getType();
}
