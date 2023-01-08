package michael.linker.gestureid.sensor.model.base;

import michael.linker.gestureid.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;

public final class MagneticFieldSensorModel extends ThreeAxisSensorModel<Float> {
    public MagneticFieldSensorModel() {
        super(BaseSensorType.MAGNETOMETER);
    }

    public MagneticFieldSensorModel(Long timestamp, Float x, Float y, Float z) {
        super(BaseSensorType.MAGNETOMETER, timestamp, x, y, z);
    }
}
