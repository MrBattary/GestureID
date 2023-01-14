package michael.linker.gestureid.data.system.gate;

import android.util.Log;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.gate.model.SystemGateWorkerModel;
import michael.linker.gestureid.data.system.gate.state.WorkerState;
import michael.linker.gestureid.data.system.gate.state.WorkerThreadState;

class SystemGateWorker implements Runnable {
    private static final String TAG = SystemGateWorker.class.getCanonicalName();

    private final Queue<AccumulatedEpisode> accumulatedEpisodeQueue;
    private final AtomicReference<WorkerThreadState> workerThreadState;
    private final AtomicReference<WorkerState> workerState;

    private int counter;

    public SystemGateWorker(SystemGateWorkerModel model) {
        this.accumulatedEpisodeQueue = model.getAccumulatedEpisodeQueue();
        this.workerThreadState = model.getWorkerThreadState();
        this.workerState = model.getWorkerState();
        counter = 0;
    }

    @Override
    public void run() {
        while (!(workerThreadState.get() == WorkerThreadState.SHUTDOWN)) {
            while (workerThreadState.get() == WorkerThreadState.WORKING) {
                try {
                    if (workerState.get() == WorkerState.AUTH_ACQUIRED) {
                        workerState.set(WorkerState.WORKING);
                        Log.i(TAG, "System gate worker acquired the auth result.");
                    }
                    if (workerState.get() == WorkerState.WORKING) {
                        if (!accumulatedEpisodeQueue.isEmpty()) {
                            // TODO(ML) : STUBS
                            accumulatedEpisodeQueue.poll();
                            Thread.sleep(100L);
                            counter++;
                            Log.i(TAG, "System gate worker processed the event.");
                            if (counter % 3 == 0) {
                                workerState.set(WorkerState.AUTH_REQUIRED);
                                Log.i(TAG, "System gate worker required the auth check.");
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i(TAG, "System gate worker was shutdown.");
    }
}
