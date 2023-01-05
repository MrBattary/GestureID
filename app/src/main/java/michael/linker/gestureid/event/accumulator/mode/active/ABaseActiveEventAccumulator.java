package michael.linker.gestureid.event.accumulator.mode.active;

import java.util.HashSet;
import java.util.Set;

public abstract class ABaseActiveEventAccumulator implements IBaseActiveEventAccumulator {
    protected final Set<IActiveEventAccumulatorListener> listenerSet;

    protected ABaseActiveEventAccumulator() {
        listenerSet = new HashSet<>();
    }

    @Override
    public void subscribe(IActiveEventAccumulatorListener listener) {
        listenerSet.add(listener);
    }

    @Override
    public void unsubscribe(IActiveEventAccumulatorListener listener) {
        listenerSet.remove(listener);
    }

    @Override
    public void unsubscribeAll() {
        listenerSet.clear();
    }
}
