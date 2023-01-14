package michael.linker.gestureid.data.system.gate;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicReference;

import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.gate.model.SystemGateWorkerModel;
import michael.linker.gestureid.data.system.gate.state.WorkerState;
import michael.linker.gestureid.data.system.gate.state.WorkerThreadState;

public class SystemGate implements ISystemGate, IActiveEventAccumulatorListener {
    private static final String TAG = SystemGate.class.getCanonicalName();

    private final BlockingQueue<AccumulatedEpisode> accumulatedEpisodeBlockingQueue;
    private final AtomicReference<WorkerThreadState> workerThreadState;
    private final AtomicReference<WorkerState> workerState;

    private ISystemGateListener authListener;

    public SystemGate() {
        accumulatedEpisodeBlockingQueue = new LinkedBlockingDeque<>();
        workerThreadState = new AtomicReference<>(WorkerThreadState.WORKING);
        workerState = new AtomicReference<>(WorkerState.WORKING);
        new Thread(
                new SystemGateWorker(
                        new SystemGateWorkerModel(
                                workerThreadState,
                                workerState,
                                accumulatedEpisodeBlockingQueue)
                )
        ).start();
    }

    @Override
    public void notifyAboutEpisode(AccumulatedEpisode accumulatedEpisode) {
        Log.i(TAG, "System gate notified about episode.");
        if (workerState.get() == WorkerState.AUTH_REQUIRED) {
            requireAuthFromListener();
        }
        if (workerState.get() == WorkerState.AUTH_ACQUIRED) {
            Log.i(TAG, "System gate received a notification about acquired authentication.");
            workerState.set(WorkerState.AUTH_ACQUIRED);
            accumulatedEpisodeBlockingQueue.add(accumulatedEpisode);
        }
        if (workerState.get() == WorkerState.AUTH_FAILED) {
            Log.i(TAG, "System gate received a notification about failed authentication.");
            workerState.set(WorkerState.AUTH_FAILED);
        }
        if (workerState.get() == WorkerState.WORKING) {
            Log.i(TAG, "System gate stores accumulated episode.");
            accumulatedEpisodeBlockingQueue.add(accumulatedEpisode);
        }
    }

    private void requireAuthFromListener() {
        Log.i(TAG, "System gate requires authentication.");
        if (authListener != null) {
            switch (authListener.requireAuth()) {
                case AUTH_ACQUIRED:
                    workerState.set(WorkerState.AUTH_ACQUIRED);
                    break;
                case AUTH_FAILED:
                    workerState.set(WorkerState.AUTH_FAILED);
                    break;
            }
        } else {
            Log.e(TAG, "System gate does not have a listener!");
            workerState.set(WorkerState.AUTH_FAILED);
        }
    }

    @Override
    public void shutdown() {
        workerThreadState.set(WorkerThreadState.SHUTDOWN);
    }

    @Override
    public void subscribe(ISystemGateListener listener) {
        authListener = listener;
    }

    @Override
    public void unsubscribe(ISystemGateListener listener) {
        if (authListener != null) {
            authListener = null;
        }
    }
}
