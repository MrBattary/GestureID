package michael.linker.gestureid.data.sensor.model.base;

import michael.linker.gestureid.data.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;

public final class AccelerometerSensorModel extends ThreeAxisSensorModel<Double> {

    public AccelerometerSensorModel() {
        super(BaseSensorType.ACCELEROMETER);
    }

    public AccelerometerSensorModel(Double timestamp, Double x, Double y, Double z) {
        super(BaseSensorType.ACCELEROMETER, timestamp, x, y, z);
    }
}
