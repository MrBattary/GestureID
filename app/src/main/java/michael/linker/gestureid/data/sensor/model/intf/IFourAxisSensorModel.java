package michael.linker.gestureid.data.sensor.model.intf;

/**
 * Interface for working with models of four axes
 *
 * @param <T> Axis type
 */
public interface IFourAxisSensorModel<T> extends IThreeAxisSensorModel<T> {
    T getW();

    void setW(T w);
}
