package michael.linker.gestureid.analyzer.calculator.far.model;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;
import michael.linker.gestureid.analyzer.calculator.addons.intersection.model.IntersectionType;

public abstract class FarDispersionTable {
    private final Double dispersion;
    private final ISquareTable<String, Double> table;

    public FarDispersionTable(Double dispersion, ISquareTable<String, Double> table) {
        this.dispersion = dispersion;
        this.table = table;
    }

    public Double getDispersion() {
        return dispersion;
    }

    public ISquareTable<String, Double> getTable() {
        return table;
    }

    public abstract IntersectionType getType();
}
