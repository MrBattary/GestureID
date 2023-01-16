package michael.linker.gestureid.data.sensor.model.base;

import michael.linker.gestureid.data.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;

public final class GyroscopeSensorModel extends ThreeAxisSensorModel<Double> {
    public GyroscopeSensorModel() {
        super(BaseSensorType.GYROSCOPE);
    }

    public GyroscopeSensorModel(Double timestamp, Double x, Double y, Double z) {
        super(BaseSensorType.GYROSCOPE, timestamp, x, y, z);
    }
}
