package michael.linker.gestureid.data.system.gate;

import androidx.lifecycle.LiveData;

import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulatorListener;

public interface ISystemGate extends IActiveEventAccumulatorListener {
    /**
     * Notify about auth result from the user.
     *
     * @param authResult auth result.
     */
    void notifyAboutAuthResult(SystemGateAuthResult authResult);

    /**
     * Shutdown stop processing and accumulation of episode events.
     * Call this on finish.
     */
    void shutdown();

    LiveData<Boolean> getAuthRequiredLiveData();
}
