package michael.linker.gestrudeid.sensor.model;

import java.util.List;
import java.util.Map;

import michael.linker.gestrudeid.sensor.type.SensorType;

/**
 * Basic sensor model for other sensor models
 */
public abstract class ASensorModel {
    protected final SensorType sensorType;
    protected Long timestamp;

    protected ASensorModel(final SensorType sensorType) {
        this.sensorType = sensorType;
    }

    protected ASensorModel(final SensorType sensorType, final Long timestamp) {
        this.sensorType = sensorType;
        this.timestamp = timestamp;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get names from model as list of names
     *
     * @return List of names
     */
    abstract public List<String> getNamesList();

    /**
     * Get values from model as list of values
     *
     * @return List of values
     */
    abstract public List<Float> getValuesList();

    /**
     * Get values from model as map of pairs name-value
     *
     * @return Map of pairs name-value
     */
    abstract public Map<String, Float> getNamesAndValuesMap();
}
