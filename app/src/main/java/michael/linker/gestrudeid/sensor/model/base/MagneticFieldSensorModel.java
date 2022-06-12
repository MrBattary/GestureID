package michael.linker.gestrudeid.sensor.model.base;

import michael.linker.gestrudeid.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;

public final class MagneticFieldSensorModel extends ThreeAxisSensorModel<Float> {
    public MagneticFieldSensorModel() {
        super(BaseSensorType.MAGNETOMETER);
    }

    public MagneticFieldSensorModel(Long timestamp, Float x, Float y, Float z) {
        super(BaseSensorType.MAGNETOMETER, timestamp, x, y, z);
    }
}
