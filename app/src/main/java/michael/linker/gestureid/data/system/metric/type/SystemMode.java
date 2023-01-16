package michael.linker.gestureid.data.system.metric.type;

public enum SystemMode {
    LEARNING(true),
    CONTROL(false);

    private final boolean mode;

    SystemMode(boolean mode) {
        this.mode = mode;
    }

    public boolean toBoolean() {
        return mode;
    }

    public static SystemMode fromBoolean(boolean mode) {
        if (mode) {
            return LEARNING;
        } else {
            return CONTROL;
        }
    }
}
