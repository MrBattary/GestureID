package michael.linker.gestureid.data.sensor.model.composite;

import michael.linker.gestureid.data.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public final class LinearAccelerationSensorModel extends ThreeAxisSensorModel<Float>{
    public LinearAccelerationSensorModel() {
        super(CompositeSensorType.LINEAR_ACCELERATION);
    }

    public LinearAccelerationSensorModel(Long timestamp, Float x, Float y, Float z) {
        super(CompositeSensorType.LINEAR_ACCELERATION, timestamp, x, y, z);
    }
}