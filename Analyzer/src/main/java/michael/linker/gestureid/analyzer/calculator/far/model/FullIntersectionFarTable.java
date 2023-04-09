package michael.linker.gestureid.analyzer.calculator.far.model;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;
import michael.linker.gestureid.analyzer.calculator.addons.intersection.model.IntersectionType;
import michael.linker.gestureid.analyzer.calculator.dispersion.model.DispersionTable;

public final class FullIntersectionFarTable extends FarDispersionTable {
    public FullIntersectionFarTable(Double dispersion, ISquareTable<String, Double> table) {
        super(dispersion, table);
    }

    @Override
    public IntersectionType getType() {
        return IntersectionType.FULL;
    }
}
