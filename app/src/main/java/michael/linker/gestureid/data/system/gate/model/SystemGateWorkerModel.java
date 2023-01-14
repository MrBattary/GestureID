package michael.linker.gestureid.data.system.gate.model;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.gate.state.WorkerState;
import michael.linker.gestureid.data.system.gate.state.WorkerThreadState;

public class SystemGateWorkerModel {
    private final AtomicReference<WorkerThreadState> workerThreadState;
    private final AtomicReference<WorkerState> workerState;
    private final Queue<AccumulatedEpisode> accumulatedEpisodeQueue;

    public SystemGateWorkerModel(
            AtomicReference<WorkerThreadState> workerThreadState,
            AtomicReference<WorkerState> workerState,
            Queue<AccumulatedEpisode> accumulatedEpisodeQueue) {
        this.workerThreadState = workerThreadState;
        this.workerState = workerState;
        this.accumulatedEpisodeQueue = accumulatedEpisodeQueue;
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
}
