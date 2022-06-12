package michael.linker.gestrudeid.sensor.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import michael.linker.gestrudeid.sensor.model.intf.IThreeAxisSensorModel;
import michael.linker.gestrudeid.sensor.type.SensorType;

/**
 * A model that includes three axis values: x,y,z
 *
 * @param <T> Axis type
 */
public class ThreeAxisSensorModel<T> extends ASensorModel<T> implements IThreeAxisSensorModel<T> {
    protected static final String X = "x";
    protected static final String Y = "y";
    protected static final String Z = "z";
    protected T x;
    protected T y;
    protected T z;

    protected ThreeAxisSensorModel(SensorType sensorType) {
        super(sensorType);
    }

    protected ThreeAxisSensorModel(SensorType sensorType, Long timestamp, T x, T y, T z) {
        super(sensorType, timestamp);
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public List<String> getNamesList() {
        return Arrays.asList(X, Y, Z);
    }
    @Override
    public List<T> getValuesList() {
        return Arrays.asList(x, y, z);
    }

    @Override
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
