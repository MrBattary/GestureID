package michael.linker.gestureid.data.sensor.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import michael.linker.gestureid.core.sensor.sensor.type.SensorAxisType;
import michael.linker.gestureid.data.sensor.model.intf.IFourAxisSensorModel;
import michael.linker.gestureid.data.sensor.model.intf.IThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

/**
 * A model that includes four axis values: x,y,z,w
 *
 * @param <T> Axis type
 */
public class FourAxisSensorModel<T> extends ASensorModel<T> implements IFourAxisSensorModel<T> {
    protected final IThreeAxisSensorModel<T> threeAxisSensorModel;
    protected T w;

    public FourAxisSensorModel(SensorType sensorType) {
        super(sensorType);
        threeAxisSensorModel = new ThreeAxisSensorModel<T>(sensorType);
    }

    public FourAxisSensorModel(SensorType sensorType, Long timestamp, T x, T y, T z, T w) {
        super(sensorType, timestamp);
        threeAxisSensorModel = new ThreeAxisSensorModel<T>(sensorType);
        threeAxisSensorModel.setX(x);
        threeAxisSensorModel.setY(y);
        threeAxisSensorModel.setZ(z);
        this.w = w;
    }

    @Override
    public T getW() {
        return w;
    }

    @Override
    public void setW(T w) {
        this.w = w;
    }

    @Override
    public List<SensorAxisType> getAxisList() {
        return Stream
                .concat(threeAxisSensorModel.getAxisList().stream(), Stream.of(SensorAxisType.W))
                .collect(Collectors.toList());
    }

    @Override
    public List<T> getValueList() {
        return Stream
                .concat(threeAxisSensorModel.getValueList().stream(), Stream.of(w))
                .collect(Collectors.toList());
    }

    @Override
    public Map<SensorAxisType, T> getAxisValueMap() {
        Map<SensorAxisType, T> buff = threeAxisSensorModel.getAxisValueMap();
        buff.put(SensorAxisType.W, w);
        return buff;
    }

    @Override
    public T getX() {
        return threeAxisSensorModel.getX();
    }

    @Override
    public void setX(T x) {
        threeAxisSensorModel.setX(x);
    }

    @Override
    public T getY() {
        return threeAxisSensorModel.getY();
    }

    @Override
    public void setY(T y) {
        threeAxisSensorModel.setY(y);
    }

    @Override
    public T getZ() {
        return threeAxisSensorModel.getZ();
    }

    @Override
    public void setZ(T z) {
        threeAxisSensorModel.setZ(z);
    }
}
