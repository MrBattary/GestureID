package michael.linker.gestureid.data.sensor.model.base;

import michael.linker.gestureid.data.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;

public final class MagneticFieldSensorModel extends ThreeAxisSensorModel<Double> {
    public MagneticFieldSensorModel() {
        super(BaseSensorType.MAGNETOMETER);
    }

    public MagneticFieldSensorModel(Double timestamp, Double x, Double y, Double z) {
        super(BaseSensorType.MAGNETOMETER, timestamp, x, y, z);
    }
}
