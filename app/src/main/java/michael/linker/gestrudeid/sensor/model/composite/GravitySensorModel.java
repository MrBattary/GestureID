package michael.linker.gestrudeid.sensor.model.composite;

import michael.linker.gestrudeid.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;

public final class GravitySensorModel extends ThreeAxisSensorModel<Float> {
    public GravitySensorModel() {
        super(CompositeSensorType.GRAVITY);
    }

    public GravitySensorModel(Long timestamp, Float x, Float y, Float z) {
        super(CompositeSensorType.GRAVITY, timestamp, x, y, z);
    }
}
