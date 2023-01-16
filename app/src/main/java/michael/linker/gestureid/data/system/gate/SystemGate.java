package michael.linker.gestureid.data.system.gate;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.gate.model.SystemGateWorkerModel;
import michael.linker.gestureid.data.system.gate.state.WorkerState;
import michael.linker.gestureid.data.system.gate.state.WorkerThreadState;

public class SystemGate implements ISystemGate {
    private static final String TAG = SystemGate.class.getCanonicalName();

    private final Queue<AccumulatedEpisode> accumulatedEpisodeQueue;
    private final AtomicReference<WorkerThreadState> workerThreadState;
    private final AtomicReference<WorkerState> workerState;
    private final MutableLiveData<Boolean> ifAuthRequired;

    public SystemGate() {
        accumulatedEpisodeQueue = new ConcurrentLinkedQueue<>();
        workerThreadState = new AtomicReference<>(WorkerThreadState.WORKING);
        workerState = new AtomicReference<>(WorkerState.WORKING);
        ifAuthRequired = new MutableLiveData<>(false);
        new Thread(
                new SystemGateWorker(
                        new SystemGateWorkerModel(
                                workerThreadState,
                                workerState,
                                accumulatedEpisodeQueue,
                                ifAuthRequired)
                )
        ).start();
    }

    @Override
    public void notifyAboutEpisode(AccumulatedEpisode accumulatedEpisode) {
        Log.i(TAG, "System gate notified about episode.");
        if (workerState.get() == WorkerState.AUTH_ACQUIRED) {
            Log.i(TAG, "System gate received a notification about acquired authentication.");
            workerState.set(WorkerState.AUTH_ACQUIRED);
            accumulatedEpisodeQueue.add(accumulatedEpisode);
        }
        if (workerState.get() == WorkerState.AUTH_FAILED) {
            Log.i(TAG, "System gate received a notification about failed authentication.");
            workerState.set(WorkerState.AUTH_FAILED);
        }
        if (workerState.get() == WorkerState.WORKING
                || workerState.get() == WorkerState.AUTH_REQUIRED) {
            Log.i(TAG, "System gate stores accumulated episode.");
            accumulatedEpisodeQueue.add(accumulatedEpisode);
        }
    }

    @Override
    public void notifyAboutAuthResult(SystemGateAuthResult authResult) {
        Log.i(TAG, "System gate notified about the auth result.");
        ifAuthRequired.postValue(false);
        switch (authResult) {
            case AUTH_ACQUIRED:
                workerState.set(WorkerState.AUTH_ACQUIRED);
                break;
            case AUTH_FAILED:
            default:
                workerState.set(WorkerState.AUTH_FAILED);
                break;
        }
    }

    @Override
    public void shutdown() {
        workerThreadState.set(WorkerThreadState.SHUTDOWN);
    }

    @Override
    public LiveData<Boolean> getAuthRequiredLiveData() {
        return ifAuthRequired;
    }
}
