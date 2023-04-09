package michael.linker.gestureid.analyzer.calculator.dispersion.model;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;
import michael.linker.gestureid.analyzer.calculator.addons.intersection.model.IntersectionType;

public final class NormalIntersectionDispersionTable extends DispersionTable {
    public NormalIntersectionDispersionTable(Double dispersion, ISquareTable<String, Integer> table) {
        super(dispersion, table);
    }

    @Override
    public IntersectionType getType() {
        return IntersectionType.FIRST_MATCH;
    }
}
