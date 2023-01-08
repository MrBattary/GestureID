package michael.linker.gestureid.sensor.manager;

public interface ISensorManager {
    /**
     * Checks if recording of events in the progress.
     *
     * @return true if in the progress, false - otherwise.
     */
    boolean isRegisteringSuppressed();

    /**
     * Pause recording of events in the accumulator.
     */
    void suppressRegistering();

    /**
     * Proceed recording of events in the accumulator.
     */
    void unsuppressRegistering();

    /**
     * Close all streams and unregister every entity.
     */
    void destroy();
}
