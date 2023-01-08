package michael.linker.gestureid.sensor.model.base;

import michael.linker.gestureid.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;

public final class AccelerometerSensorModel extends ThreeAxisSensorModel<Float> {

    public AccelerometerSensorModel() {
        super(BaseSensorType.ACCELEROMETER);
    }

    public AccelerometerSensorModel(Long timestamp, Float x, Float y, Float z) {
        super(BaseSensorType.ACCELEROMETER, timestamp, x, y, z);
    }
}
