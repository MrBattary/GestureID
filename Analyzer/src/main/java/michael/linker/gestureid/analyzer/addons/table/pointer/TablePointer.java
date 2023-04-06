package michael.linker.gestureid.analyzer.addons.table.pointer;

public class TablePointer<R, C> {
    private final R row;
    private final C column;

    public TablePointer(R row, C column) {
        this.row = row;
        this.column = column;
    }

    public R getRow() {
        return row;
    }

    public C getColumn() {
        return column;
    }
}
