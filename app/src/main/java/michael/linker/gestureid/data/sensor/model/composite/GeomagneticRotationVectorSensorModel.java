package michael.linker.gestureid.data.sensor.model.composite;

import michael.linker.gestureid.data.sensor.model.FourAxisSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public final class GeomagneticRotationVectorSensorModel extends FourAxisSensorModel<Double> {
    public GeomagneticRotationVectorSensorModel() {
        super(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR);
    }

    public GeomagneticRotationVectorSensorModel(Double timestamp, Double x, Double y, Double z, Double w) {
        super(CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR, timestamp, x, y, z, w);
    }
}
