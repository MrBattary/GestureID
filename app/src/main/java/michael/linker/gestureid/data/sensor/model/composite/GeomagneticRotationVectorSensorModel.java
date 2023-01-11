package michael.linker.gestureid.data.sensor.model.composite;

import michael.linker.gestureid.data.sensor.model.FourAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public final class GeomagneticRotationVectorSensorModel extends FourAxisSensorModel<Float> {
    public GeomagneticRotationVectorSensorModel() {
        super(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR);
    }

    public GeomagneticRotationVectorSensorModel(Long timestamp, Float x, Float y, Float z, Float w) {
        super(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR, timestamp, x, y, z, w);
    }
}
