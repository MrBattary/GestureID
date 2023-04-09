package michael.linker.gestureid.analyzer.sensor;

public enum SensorAxisType {
    X("x"),
    Y("y"),
    Z("z"),
    W("w"),
    UNDEFINED("UNDEFINED");
    private final String axis;

    SensorAxisType(String axis) {
        this.axis = axis;
    }

    @Override
    public String toString() {
        return axis;
    }
}
