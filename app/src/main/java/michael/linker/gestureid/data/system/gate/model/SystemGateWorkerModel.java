package michael.linker.gestureid.data.system.gate.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.gate.state.WorkerState;
import michael.linker.gestureid.data.system.gate.state.WorkerThreadState;

public class SystemGateWorkerModel {
    private final AtomicReference<WorkerThreadState> workerThreadState;
    private final AtomicReference<WorkerState> workerState;
    private final BlockingQueue<AccumulatedEpisode> accumulatedEpisodeBlockingQueue;

    public SystemGateWorkerModel(
            AtomicReference<WorkerThreadState> workerThreadState,
            AtomicReference<WorkerState> workerState,
            BlockingQueue<AccumulatedEpisode> accumulatedEpisodeBlockingQueue) {
        this.workerThreadState = workerThreadState;
        this.workerState = workerState;
        this.accumulatedEpisodeBlockingQueue = accumulatedEpisodeBlockingQueue;
    }

    public AtomicReference<WorkerThreadState> getWorkerThreadState() {
        return workerThreadState;
    }

    public AtomicReference<WorkerState> getWorkerState() {
        return workerState;
    }

    public BlockingQueue<AccumulatedEpisode> getAccumulatedEpisodeBlockingQueue() {
        return accumulatedEpisodeBlockingQueue;
    }
}
