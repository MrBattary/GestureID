package michael.linker.gestureid.analyzer.calculator.dispersion.model;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;
import michael.linker.gestureid.analyzer.calculator.addons.intersection.model.IntersectionType;
import michael.linker.gestureid.analyzer.calculator.dispersion.model.DispersionTable;

public final class FullIntersectionDispersionTable extends DispersionTable {
    public FullIntersectionDispersionTable(Double dispersion, ISquareTable<String, Integer> table) {
        super(dispersion, table);
    }

    @Override
    public IntersectionType getType() {
        return IntersectionType.FULL;
    }
}
