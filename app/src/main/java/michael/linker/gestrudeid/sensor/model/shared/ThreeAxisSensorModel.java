package michael.linker.gestrudeid.sensor.model.shared;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A model that includes three axis values: x,y,z
 *
 * @param <T> Axis type
 */
public class ThreeAxisSensorModel<T> implements IThreeAxisSensorModel<T> {
    private static final String X = "x";
    private static final String Y = "y";
    private static final String Z = "z";
    private T x;
    private T y;
    private T z;

    public ThreeAxisSensorModel() {
    }

    public ThreeAxisSensorModel(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public List<String> getNamesList() {
        return Arrays.asList(X, Y, Z);
    }

    public List<T> getValuesList() {
        return Arrays.asList(x, y, z);
    }

    public Map<String, T> getNamesAndValuesMap() {
        Map<String, T> valuesMap = new HashMap<>();
        valuesMap.put(X, x);
        valuesMap.put(Y, y);
        valuesMap.put(Z, z);
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
