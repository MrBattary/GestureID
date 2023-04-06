package michael.linker.gestureid.analyzer.addons.table;

import michael.linker.gestureid.analyzer.addons.table.pointer.TablePointer;
import michael.linker.gestureid.analyzer.addons.table.pointer.ValuedTablePointer;

import java.util.Set;

public interface ITable<R, C, V> {
    V getValue(TablePointer<R, C> pointer);

    void setValue(ValuedTablePointer<R, C, V> valuedPointer);

    Set<R> getRows();

    Set<C> getColumns();
}
