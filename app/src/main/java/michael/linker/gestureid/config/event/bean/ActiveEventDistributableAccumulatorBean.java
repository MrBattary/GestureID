package michael.linker.gestureid.config.event.bean;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulator;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.data.event.accumulator.overflow.EventAccumulatorOverflowException;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;

public class ActiveEventDistributableAccumulatorBean
        extends ConfigurationBean<IActiveEventAccumulator>
        implements IActiveEventAccumulator {

    public ActiveEventDistributableAccumulatorBean(
            IActiveEventAccumulator defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public IActiveEventAccumulator implement(IActiveEventAccumulator newImplementation) {
        setImplementation(newImplementation);
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
}
