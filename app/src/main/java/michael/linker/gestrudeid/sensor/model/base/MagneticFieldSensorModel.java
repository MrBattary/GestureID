package michael.linker.gestrudeid.sensor.model.base;

import java.util.List;
import java.util.Map;

import michael.linker.gestrudeid.sensor.model.ASensorModel;
import michael.linker.gestrudeid.sensor.model.shared.IThreeAxisSensorModel;
import michael.linker.gestrudeid.sensor.model.shared.ThreeAxisSensorModel;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;

public class MagneticFieldSensorModel extends ASensorModel implements IThreeAxisSensorModel<Float> {
    private ThreeAxisSensorModel<Float> threeAxisSensorModel;

    public MagneticFieldSensorModel() {
        super(BaseSensorType.MAGNETOMETER);
    }

    public MagneticFieldSensorModel(Float x, Float y, Float z) {
        super(BaseSensorType.MAGNETOMETER);
        threeAxisSensorModel = new ThreeAxisSensorModel<>(x, y, z);
    }

    @Override
    public List<String> getNamesList() {
        return threeAxisSensorModel.getNamesList();
    }

    @Override
    public List<Float> getValuesList() {
        return threeAxisSensorModel.getValuesList();
    }

    @Override
    public Map<String, Float> getNamesAndValuesMap() {
        return threeAxisSensorModel.getNamesAndValuesMap();
    }

    @Override
    public Float getX() {
        return threeAxisSensorModel.getX();
    }

    @Override
    public void setX(Float x) {
        threeAxisSensorModel.setX(x);
    }

    @Override
    public Float getY() {
        return threeAxisSensorModel.getY();
    }

    @Override
    public void setY(Float y) {
        threeAxisSensorModel.setY(y);
    }

    @Override
    public Float getZ() {
        return threeAxisSensorModel.getZ();
    }

    @Override
    public void setZ(Float z) {
        threeAxisSensorModel.setZ(z);
    }
}
