package michael.linker.gestrudeid.sensor.model.intf;

import java.util.List;
import java.util.Map;

/**
 * Interface for working with models of three axes
 *
 * @param <T> Axis type
 */
public interface IThreeAxisSensorModel<T> {
    List<String> getNamesList();

    List<T> getValuesList();

    Map<String, T> getNamesAndValuesMap();

    T getX();

    void setX(T x);

    T getY();

    void setY(T y);

    T getZ();

    void setZ(T z);
}
