package michael.linker.gestureid.analyzer.addons.table.square.pointer;

import michael.linker.gestureid.analyzer.addons.table.pointer.TablePointer;

public class SquareTablePointer<K> extends TablePointer<K, K> {
    public SquareTablePointer(K row, K column) {
        super(row, column);
    }
}
