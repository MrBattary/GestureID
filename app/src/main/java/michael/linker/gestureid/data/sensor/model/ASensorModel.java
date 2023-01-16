package michael.linker.gestureid.data.sensor.model;

import java.util.List;
import java.util.Map;

import michael.linker.gestureid.core.sensor.sensor.type.SensorAxisType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

/**
 * Basic sensor model for other sensor models
 *
 * @param <T> Axis type
 */
public abstract class ASensorModel<T> {
    protected final SensorType sensorType;
    protected Double timestamp;

    protected ASensorModel(final SensorType sensorType) {
        this.sensorType = sensorType;
    }

    protected ASensorModel(final SensorType sensorType, final Double timestamp) {
        this.sensorType = sensorType;
        this.timestamp = timestamp;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get names from model as list of names
     *
     * @return List of names
     */
    abstract public List<SensorAxisType> getAxisList();

    /**
     * Get values from model as list of values
     *
     * @return List of values
     */
    abstract public List<T> getValueList();

    /**
     * Get values from model as map of pairs name-value
     *
     * @return Map of pairs name-value
     */
    abstract public Map<SensorAxisType, T> getAxisValueMap();
}
