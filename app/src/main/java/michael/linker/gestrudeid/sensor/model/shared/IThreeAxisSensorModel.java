package michael.linker.gestrudeid.sensor.model.shared;

/**
 * Interface for working with models of three axes
 *
 * @param <T> Axis type
 */
public interface IThreeAxisSensorModel<T> {
    T getX();

    void setX(T x);

    T getY();

    void setY(T y);

    T getZ();

    void setZ(T z);
}
