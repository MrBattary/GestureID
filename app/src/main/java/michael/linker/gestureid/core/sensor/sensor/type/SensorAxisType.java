package michael.linker.gestureid.core.sensor.sensor.type;

import androidx.annotation.NonNull;

public class SensorAxisType {
    public static final SensorAxisType X;
    public static final SensorAxisType Y;
    public static final SensorAxisType Z;
    public static final SensorAxisType W;

    static {
        X = new SensorAxisType("x");
        Y = new SensorAxisType("y");
        Z = new SensorAxisType("z");
        W = new SensorAxisType("w");
    }

    private final String axis;

    public SensorAxisType(String axis) {
        this.axis = axis;
    }

    @NonNull
    @Override
    public String toString() {
        return axis;
    }
}
