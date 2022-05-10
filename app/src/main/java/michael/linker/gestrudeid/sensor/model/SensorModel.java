package michael.linker.gestrudeid.sensor.model;

import michael.linker.gestrudeid.sensor.types.SensorType;

/**
 * Basic sensor model for other sensor models
 */
public class SensorModel {
    protected SensorType sensorType;

    public SensorModel(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public SensorType getSensorType() {
        return sensorType;
    }
}
