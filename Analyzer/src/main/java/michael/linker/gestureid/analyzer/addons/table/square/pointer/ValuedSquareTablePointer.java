package michael.linker.gestureid.analyzer.addons.table.square.pointer;

import michael.linker.gestureid.analyzer.addons.table.pointer.ValuedTablePointer;

public class ValuedSquareTablePointer<K, V> extends ValuedTablePointer<K, K, V> {

    public ValuedSquareTablePointer(K row, K column, V value) {
        super(row, column, value);
    }
}
