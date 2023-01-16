package michael.linker.gestureid.config.event.bean;

import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveFlushableEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;

public class ActiveEventFlushableAccumulatorBean
        extends ActiveEventDistributableAccumulatorBean
        implements IActiveFlushableEventAccumulator {

    public ActiveEventFlushableAccumulatorBean(
            IActiveFlushableEventAccumulator defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public IActiveEventAccumulator implement(IActiveEventAccumulator newImplementation) {
        if (newImplementation instanceof IActiveFlushableEventAccumulator) {
            setImplementation(newImplementation);
        }
        return getImplementation();
    }

    @Override
    public void accumulate(SynchronizedEvent synchronizedEvent)
            throws EventAccumulatorOverflowException {
        getImplementation().accumulate(synchronizedEvent);
    }

    @Override
    public int getMaxSize() {
        return getImplementation().getMaxSize();
    }

    @Override
    public int getSize() {
        return getImplementation().getSize();
    }

    @Override
    public void subscribe(IActiveEventAccumulatorListener listener) {
        getImplementation().subscribe(listener);
    }

    @Override
    public void unsubscribe(IActiveEventAccumulatorListener listener) {
        getImplementation().unsubscribe(listener);
    }

    @Override
    public void unsubscribeAll() {
        getImplementation().unsubscribeAll();
    }

    @Override
    public void startAccumulation() {
        ((IActiveFlushableEventAccumulator) getImplementation()).startAccumulation();
    }

    @Override
    public void flush() {
        ((IActiveFlushableEventAccumulator) getImplementation()).flush();
    }
}
