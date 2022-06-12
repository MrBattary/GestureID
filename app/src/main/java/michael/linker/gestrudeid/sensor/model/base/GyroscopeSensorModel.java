package michael.linker.gestrudeid.sensor.model.base;

import michael.linker.gestrudeid.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;

public final class GyroscopeSensorModel extends ThreeAxisSensorModel<Float> {
    public GyroscopeSensorModel() {
        super(BaseSensorType.GYROSCOPE);
    }

    public GyroscopeSensorModel(Long timestamp, Float x, Float y, Float z) {
        super(BaseSensorType.GYROSCOPE, timestamp, x, y, z);
    }
}
