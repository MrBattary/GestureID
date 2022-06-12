package michael.linker.gestrudeid.sensor.model.intf;

/**
 * Interface for working with models of four axes
 *
 * @param <T> Axis type
 */
public interface IFourAxisSensorModel<T> extends IThreeAxisSensorModel<T> {
    T getW();

    void setW(T w);
}
