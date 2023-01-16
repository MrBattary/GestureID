package michael.linker.gestureid.data.system.gate;

import android.util.Log;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.gate.model.SystemGateWorkerModel;
import michael.linker.gestureid.data.system.gate.state.WorkerState;
import michael.linker.gestureid.data.system.gate.state.WorkerThreadState;
import michael.linker.gestureid.data.system.processor.ISystemProcessor;
import michael.linker.gestureid.data.system.processor.SystemProcessor;
import michael.linker.gestureid.data.system.processor.type.SystemProcessorResult;

class SystemGateWorker implements Runnable {
    private static final String TAG = SystemGateWorker.class.getCanonicalName();

    private final Queue<AccumulatedEpisode> accumulatedEpisodeQueue;
    private final AtomicReference<WorkerThreadState> workerThreadState;
    private final AtomicReference<WorkerState> workerState;

    private final ISystemProcessor systemProcessor;

    public SystemGateWorker(SystemGateWorkerModel model) {
        this.accumulatedEpisodeQueue = model.getAccumulatedEpisodeQueue();
        this.workerThreadState = model.getWorkerThreadState();
        this.workerState = model.getWorkerState();
        systemProcessor = new SystemProcessor();
    }

    @Override
    public void run() {
        Log.i(TAG, "System gate worker has been started.");
        while (!(workerThreadState.get() == WorkerThreadState.SHUTDOWN)) {
            while (workerThreadState.get() == WorkerThreadState.WORKING) {
                if (workerState.get() == WorkerState.AUTH_ACQUIRED) {
                    systemProcessor.authAcquired();
                    workerState.set(WorkerState.WORKING);
                    Log.i(TAG, "System gate worker acquired the auth result.");
                }
                if (workerState.get() == WorkerState.WORKING) {
                    if (!accumulatedEpisodeQueue.isEmpty()) {
                        SystemProcessorResult result = systemProcessor
                                .proceed(accumulatedEpisodeQueue.poll());
                        if (result == SystemProcessorResult.AUTH_REQUIRED) {
                            workerState.set(WorkerState.AUTH_REQUIRED);
                            Log.i(TAG, "System gate worker required the auth check.");
                        } else {
                            Log.i(TAG, "System gate worker processed the event.");
                        }
                    }
                }
            }
        }
        systemProcessor.saveData();
        Log.i(TAG, "System gate worker was shutdown.");
    }
}
