package michael.linker.gestureid.sensor.manager;

public interface ISensorManager {
    /**
     * Pause recording of events in the output stream
     */
    void suppressRegistering();

    /**
     * Proceed recording of events in the output stream
     */
    void unsuppressRegistering();

    /**
     * Close all streams and unregister every entity
     */
    void destroy();
}
