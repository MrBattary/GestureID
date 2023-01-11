package michael.linker.gestureid.data.sensor.model.composite;

import michael.linker.gestureid.data.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public final class GravitySensorModel extends ThreeAxisSensorModel<Float> {
    public GravitySensorModel() {
        super(CompositeSensorType.GRAVITY);
    }

    public GravitySensorModel(Long timestamp, Float x, Float y, Float z) {
        super(CompositeSensorType.GRAVITY, timestamp, x, y, z);
    }
}
