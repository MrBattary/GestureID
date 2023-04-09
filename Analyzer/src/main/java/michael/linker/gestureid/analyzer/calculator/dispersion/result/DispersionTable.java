package michael.linker.gestureid.analyzer.calculator.dispersion.result;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;

public abstract class DispersionTable {
    private final Double dispersion;
    private final ISquareTable<String, Integer> table;

    public DispersionTable(Double dispersion, ISquareTable<String, Integer> table) {
        this.dispersion = dispersion;
        this.table = table;
    }

    public Double getDispersion() {
        return dispersion;
    }

    public ISquareTable<String, Integer> getTable() {
        return table;
    }

    public abstract IntersectionType getType();
}
