package michael.linker.gestureid.data.system.gate;

public interface ISystemGate {
    /**
     * Start processing and accumulation of episode events.
     * Call this on start.
     */
    //void start();

    /**
     * Shutdown stop processing and accumulation of episode events.
     * Call this on finish.
     */
    void shutdown();

    /**
     * Subscribe listener to the system gate requests.
     *
     * @param listener system gate listener.
     */
    void subscribe(ISystemGateListener listener);

    /**
     * Unsubscribe listener from the system gate requests.
     *
     * @param listener system gate listener.
     */
    void unsubscribe(ISystemGateListener listener);
}
