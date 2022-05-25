package michael.linker.gestrudeid.sensor.model.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A model that includes three axis values: x,y,z
 *
 * @param <T> Axis type
 */
public class ThreeAxisSensorModel<T> implements IThreeAxisSensorModel<T> {
    private T x;
    private T y;
    private T z;

    public ThreeAxisSensorModel(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public List<T> getValuesList() {
        List<T> valuesList = new ArrayList<>();
        valuesList.add(x);
        valuesList.add(y);
        valuesList.add(z);
        return valuesList;
    }

    public Map<String, T> getNamesAndValuesMap() {
        Map<String, T> valuesMap = new HashMap<>();
        valuesMap.put("x", x);
        valuesMap.put("y", y);
        valuesMap.put("z", z);
        return valuesMap;
    }

    @Override
    public T getX() {
        return x;
    }

    @Override
    public void setX(T x) {
        this.x = x;
    }

    @Override
    public T getY() {
        return y;
    }

    @Override
    public void setY(T y) {
        this.y = y;
    }

    @Override
    public T getZ() {
        return z;
    }

    @Override
    public void setZ(T z) {
        this.z = z;
    }
}
