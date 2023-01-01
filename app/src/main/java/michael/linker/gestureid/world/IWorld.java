package michael.linker.gestureid.world;

public interface IWorld {
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
