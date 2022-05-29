package michael.linker.gestrudeid.sensor.type;

import androidx.annotation.NonNull;

public class SensorType {
    private final int typeInt;
    private final String type;

    public SensorType(final int typeId, final String type) {
        this.typeInt = typeId;
        this.type = type;
    }

    public int toInt() {
        return typeInt;
    }

    @NonNull
    @Override
    public String toString() {
        return type;
    }
}
