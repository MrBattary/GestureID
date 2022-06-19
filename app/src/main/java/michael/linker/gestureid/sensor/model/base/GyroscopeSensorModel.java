package michael.linker.gestureid.sensor.model.base;

import michael.linker.gestureid.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.sensor.type.BaseSensorType;

public final class GyroscopeSensorModel extends ThreeAxisSensorModel<Float> {
    public GyroscopeSensorModel() {
        super(BaseSensorType.GYROSCOPE);
    }

    public GyroscopeSensorModel(Long timestamp, Float x, Float y, Float z) {
        super(BaseSensorType.GYROSCOPE, timestamp, x, y, z);
    }
}
