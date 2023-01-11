package michael.linker.gestureid.data.sensor.model.composite;

import michael.linker.gestureid.data.sensor.model.FourAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public final class RotationVectorSensorModel extends FourAxisSensorModel<Float> {
    public RotationVectorSensorModel() {
        super(CompositeSensorType.ROTATION_VECTOR);
    }

    public RotationVectorSensorModel(Long timestamp, Float x, Float y, Float z, Float w) {
        super(CompositeSensorType.ROTATION_VECTOR, timestamp, x, y, z, w);
    }
}
