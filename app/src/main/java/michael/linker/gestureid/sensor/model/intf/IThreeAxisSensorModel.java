package michael.linker.gestureid.sensor.model.intf;

import java.util.List;
import java.util.Map;

import michael.linker.gestureid.core.sensor.sensor.type.SensorAxisType;

/**
 * Interface for working with models of three axes
 *
 * @param <T> Axis type
 */
public interface IThreeAxisSensorModel<T> {
    List<SensorAxisType> getAxisList();

    List<T> getValueList();

    Map<SensorAxisType, T> getAxisValueMap();

    T getX();

    void setX(T x);

    T getY();

    void setY(T y);

    T getZ();

    void setZ(T z);
}
