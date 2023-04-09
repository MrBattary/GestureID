package michael.linker.gestureid.analyzer.addons.table.square;

import michael.linker.gestureid.analyzer.addons.table.TableValueNotFoundException;
import michael.linker.gestureid.analyzer.addons.table.pointer.TablePointer;
import michael.linker.gestureid.analyzer.addons.table.pointer.ValuedTablePointer;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.SquareTablePointer;
import michael.linker.gestureid.analyzer.addons.table.square.pointer.ValuedSquareTablePointer;

import java.util.*;

public class SquareTable<K, V> implements ISquareTable<K, V> {
    private final Set<K> rowsAndColumns;
    private final Map<K, Map<K, V>> table;

    public SquareTable(Set<K> keys, V defaultValue) {
        this.rowsAndColumns = keys;
        this.table = new HashMap<>();
        fillTableWithDefaultValues(defaultValue);
    }

    private void fillTableWithDefaultValues(V defaultValue) {
        for (K row : rowsAndColumns) {
            HashMap<K, V> rowMap = new HashMap<>();
            for (K column : rowsAndColumns) {
                rowMap.put(column, defaultValue);
            }
            table.put(row, rowMap);
        }
    }

    @Override
    public V getValue(TablePointer<K, K> pointer) throws TableValueNotFoundException {
        if (!rowsAndColumns.contains(pointer.getRow()) || !rowsAndColumns.contains(pointer.getColumn())) {
            throw new TableValueNotFoundException((ValuedSquareTablePointer<?, ?>) pointer);
        }
        Map<K, V> row = table.get(pointer.getRow());
        return row.get(pointer.getColumn());
    }

    @Override
    public void setValue(ValuedTablePointer<K, K, V> valuedPointer) {
        Map<K, V> row = table.get(valuedPointer.getRow());
        row.put(valuedPointer.getColumn(), valuedPointer.getValue());
    }

    @Override
    public List<V> getValuesForRow(K row) throws TableValueNotFoundException {
        List<V> rowValues = new ArrayList<>();
        for (K column : getColumns()) {
            rowValues.add(getValue(new SquareTablePointer<>(row, column)));
        }
        return rowValues;
    }

    @Override
    public Set<K> getRows() {
        return rowsAndColumns;
    }

    @Override
    public Set<K> getColumns() {
        return rowsAndColumns;
    }

    @Override
    public V getValue(SquareTablePointer<K> pointer) throws TableValueNotFoundException {
        return getValue((TablePointer<K, K>) pointer);
    }

    @Override
    public void setValue(ValuedSquareTablePointer<K, V> valuedPointer) {
        setValue((ValuedTablePointer<K, K, V>) valuedPointer);
    }
}
