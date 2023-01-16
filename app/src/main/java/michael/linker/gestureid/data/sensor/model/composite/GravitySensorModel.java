package michael.linker.gestureid.data.sensor.model.composite;

import michael.linker.gestureid.data.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public final class GravitySensorModel extends ThreeAxisSensorModel<Double> {
    public GravitySensorModel() {
        super(CompositeSensorType.GRAVITY);
    }

    public GravitySensorModel(Double timestamp, Double x, Double y, Double z) {
        super(CompositeSensorType.GRAVITY, timestamp, x, y, z);
    }
}
