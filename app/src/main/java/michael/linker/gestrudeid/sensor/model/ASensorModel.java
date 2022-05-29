package michael.linker.gestrudeid.sensor.model;

import java.util.List;
import java.util.Map;

import michael.linker.gestrudeid.sensor.type.SensorType;

/**
 * Basic sensor model for other sensor models
 */
public abstract class ASensorModel {
    protected SensorType sensorType;

    protected ASensorModel(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public SensorType getSensorType() {
        return sensorType;
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
