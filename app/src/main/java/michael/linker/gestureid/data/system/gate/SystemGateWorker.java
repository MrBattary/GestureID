package michael.linker.gestureid.data.system.gate;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.gate.model.SystemGateWorkerModel;
import michael.linker.gestureid.data.system.gate.state.WorkerState;
import michael.linker.gestureid.data.system.gate.state.WorkerThreadState;

class SystemGateWorker implements Runnable {
    private final BlockingQueue<AccumulatedEpisode> accumulatedEpisodeBlockingQueue;
    private final AtomicReference<WorkerThreadState> workerThreadState;
    private final AtomicReference<WorkerState> workerState;

    public SystemGateWorker(SystemGateWorkerModel model) {
        this.accumulatedEpisodeBlockingQueue = model.getAccumulatedEpisodeBlockingQueue();
        this.workerThreadState = model.getWorkerThreadState();
        this.workerState = model.getWorkerState();
    }

    @Override
    public void run() {
        while (!(workerThreadState.get() == WorkerThreadState.SHUTDOWN)) {
            while (!(workerThreadState.get() == WorkerThreadState.SLEEP)) {
                try {
                    accumulatedEpisodeBlockingQueue.take();
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
