package michael.linker.gestureid.data.sensor.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import michael.linker.gestureid.core.sensor.sensor.type.SensorAxisType;
import michael.linker.gestureid.data.sensor.model.intf.IThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

/**
 * A model that includes three axis values: x,y,z
 *
 * @param <T> Axis type
 */
public class ThreeAxisSensorModel<T> extends ASensorModel<T> implements IThreeAxisSensorModel<T> {
    protected T x;
    protected T y;
    protected T z;

    protected ThreeAxisSensorModel(SensorType sensorType) {
        super(sensorType);
    }

    protected ThreeAxisSensorModel(SensorType sensorType, Double timestamp, T x, T y, T z) {
        super(sensorType, timestamp);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public List<SensorAxisType> getAxisList() {
        return Arrays.asList(SensorAxisType.X, SensorAxisType.Y, SensorAxisType.Z);
    }

    @Override
    public List<T> getValueList() {
        return Arrays.asList(x, y, z);
    }

    @Override
    public Map<SensorAxisType, T> getAxisValueMap() {
        Map<SensorAxisType, T> valuesMap = new HashMap<>();
        valuesMap.put(SensorAxisType.X, x);
        valuesMap.put(SensorAxisType.Y, y);
        valuesMap.put(SensorAxisType.Z, z);
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
