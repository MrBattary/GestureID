package michael.linker.gestureid.analyzer.addons.table.square;

import michael.linker.gestureid.analyzer.addons.table.ITable;
import michael.linker.gestureid.analyzer.addons.table.TableValueNotFoundException;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.SquareTablePointer;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.ValuedSquareTablePointer;

public interface ISquareTable<K, V> extends ITable<K, K, V> {
    V getValue(SquareTablePointer<K> pointer) throws TableValueNotFoundException;

    void setValue(ValuedSquareTablePointer<K, V> valuedPointer);
}
