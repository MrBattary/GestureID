package michael.linker.gestureid.analyzer.addons.table.pointer;

public class ValuedTablePointer<R, C, V> extends TablePointer<R, C> {
    private final V value;

    public ValuedTablePointer(R row, C column, V value) {
        super(row, column);
        this.value = value;
    }

    public ValuedTablePointer(TablePointer<R, C> pointer, V value) {
        super(pointer.getRow(), pointer.getColumn());
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}
