package michael.linker.gestureid.core.sensor.sensor.type;

import androidx.annotation.NonNull;

public enum SensorAxisType {
    X("x"),
    Y("y"),
    Z("z"),
    W("w");

    private final String axis;
    SensorAxisType(String axis) {
        this.axis = axis;
    }

    @NonNull
    @Override
    public String toString() {
        return axis;
    }
}
