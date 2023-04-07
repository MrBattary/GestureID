package michael.linker.gestureid.analyzer.calculator.model.result;

import michael.linker.gestureid.analyzer.addons.table.square.ISquareTable;

public record CalculationTable(Double dispersion, ISquareTable<String, Double> table) {
}
