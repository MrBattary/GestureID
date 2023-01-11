package michael.linker.gestureid.data.system;

public class SystemMode {
    public static final SystemMode LEARNING;
    public static final SystemMode CONTROL;

    static {
        LEARNING = new SystemMode(true);
        CONTROL = new SystemMode(false);
    }

    private final boolean mode;

    public SystemMode(boolean mode) {
        this.mode = mode;
    }

    public boolean toBoolean() {
        return mode;
    }

    public static SystemMode parse(boolean mode) {
        if (mode) {
            return LEARNING;
        } else {
            return CONTROL;
        }
    }
}
