package michael.linker.gestureid.data.system.gate.model;

import androidx.lifecycle.MutableLiveData;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.gate.state.WorkerState;
import michael.linker.gestureid.data.system.gate.state.WorkerThreadState;

public class SystemGateWorkerModel {
    private final AtomicReference<WorkerThreadState> workerThreadState;
    private final AtomicReference<WorkerState> workerState;
    private final Queue<AccumulatedEpisode> accumulatedEpisodeQueue;
    private final MutableLiveData<Boolean> ifAuthRequired;

    public SystemGateWorkerModel(
            AtomicReference<WorkerThreadState> workerThreadState,
            AtomicReference<WorkerState> workerState,
            Queue<AccumulatedEpisode> accumulatedEpisodeQueue,
            MutableLiveData<Boolean> ifAuthRequired) {
        this.workerThreadState = workerThreadState;
        this.workerState = workerState;
        this.accumulatedEpisodeQueue = accumulatedEpisodeQueue;
        this.ifAuthRequired = ifAuthRequired;
    }

    public AtomicReference<WorkerThreadState> getWorkerThreadState() {
        return workerThreadState;
    }

    public AtomicReference<WorkerState> getWorkerState() {
        return workerState;
    }

    public Queue<AccumulatedEpisode> getAccumulatedEpisodeQueue() {
        return accumulatedEpisodeQueue;
    }

    public MutableLiveData<Boolean> getIfAuthRequired() {
        return ifAuthRequired;
    }
}
