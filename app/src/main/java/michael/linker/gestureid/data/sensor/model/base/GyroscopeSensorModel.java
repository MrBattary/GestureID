package michael.linker.gestureid.data.sensor.model.base;

import michael.linker.gestureid.data.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;

public final class GyroscopeSensorModel extends ThreeAxisSensorModel<Float> {
    public GyroscopeSensorModel() {
        super(BaseSensorType.GYROSCOPE);
    }

    public GyroscopeSensorModel(Long timestamp, Float x, Float y, Float z) {
        super(BaseSensorType.GYROSCOPE, timestamp, x, y, z);
    }
}
