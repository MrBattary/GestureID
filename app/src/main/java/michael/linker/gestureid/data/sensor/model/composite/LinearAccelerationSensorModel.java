package michael.linker.gestureid.data.sensor.model.composite;

import michael.linker.gestureid.data.sensor.model.ThreeAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public final class LinearAccelerationSensorModel extends ThreeAxisSensorModel<Double> {
    public LinearAccelerationSensorModel() {
        super(CompositeSensorType.LINEAR_ACCELERATION);
    }

    public LinearAccelerationSensorModel(Double timestamp, Double x, Double y, Double z) {
        super(CompositeSensorType.LINEAR_ACCELERATION, timestamp, x, y, z);
    }
}
