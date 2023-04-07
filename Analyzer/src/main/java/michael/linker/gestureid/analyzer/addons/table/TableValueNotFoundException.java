package michael.linker.gestureid.analyzer.addons.table;

import michael.linker.gestureid.analyzer.addons.table.square.pointer.ValuedSquareTablePointer;

public class TableValueNotFoundException extends RuntimeException {
    private static final String MSG = "The value in row %1$s of column %2$s was not found in the table!";

    public TableValueNotFoundException(ValuedSquareTablePointer<?, ?> tablePointer) {
        super(String.format(MSG, tablePointer.getRow().toString(), tablePointer.getColumn().toString()));
    }
}
