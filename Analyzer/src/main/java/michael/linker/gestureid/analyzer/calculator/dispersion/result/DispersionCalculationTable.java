package michael.linker.gestureid.analyzer.calculator.dispersion.result;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;

public record DispersionCalculationTable(Double dispersion, ISquareTable<String, Integer> table) {
}
