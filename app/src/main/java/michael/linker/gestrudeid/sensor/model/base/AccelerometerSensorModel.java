package michael.linker.gestrudeid.sensor.model.base;

import michael.linker.gestrudeid.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;

public final class AccelerometerSensorModel extends ThreeAxisSensorModel<Float> {

    public AccelerometerSensorModel() {
        super(BaseSensorType.ACCELEROMETER);
    }

    public AccelerometerSensorModel(Long timestamp, Float x, Float y, Float z) {
        super(BaseSensorType.ACCELEROMETER, timestamp, x, y, z);
    }
}
