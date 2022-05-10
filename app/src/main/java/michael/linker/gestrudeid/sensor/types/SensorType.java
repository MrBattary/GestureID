package michael.linker.gestrudeid.sensor.types;

public class SensorType {
    private final int typeInt;

    public SensorType(final int typeId) {
        this.typeInt = typeId;
    }

    public int toInt() {
        return typeInt;
    }
}
