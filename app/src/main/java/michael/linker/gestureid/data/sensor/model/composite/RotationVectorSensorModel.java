package michael.linker.gestureid.data.sensor.model.composite;

import michael.linker.gestureid.data.sensor.model.FourAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public final class RotationVectorSensorModel extends FourAxisSensorModel<Double> {
    public RotationVectorSensorModel() {
        super(CompositeSensorType.ROTATION_VECTOR);
    }

    public RotationVectorSensorModel(Double timestamp, Double x, Double y, Double z, Double w) {
        super(CompositeSensorType.ROTATION_VECTOR, timestamp, x, y, z, w);
    }
}
