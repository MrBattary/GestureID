package michael.linker.gestureid.analyzer.calculator.dispersion.result;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;

public class FullIntersectionDispersionTable extends DispersionTable {
    public FullIntersectionDispersionTable(Double dispersion, ISquareTable<String, Integer> table) {
        super(dispersion, table);
    }

    @Override
    public IntersectionType getType() {
        return IntersectionType.FULL;
    }
}
