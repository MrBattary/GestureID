package michael.linker.gestureid.data.system.gate;

import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulatorListener;

public interface ISystemGate extends IActiveEventAccumulatorListener {
    /**
     * Start processing and accumulation of episode events.
     * Call this on start.
     */
    //void start();

    void notifyAboutAuthResult(SystemGateAuthResult authResult);

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
     */
    void unsubscribe();
}
