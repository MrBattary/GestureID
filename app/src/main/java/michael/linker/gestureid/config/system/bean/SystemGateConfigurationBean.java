package michael.linker.gestureid.config.system.bean;

import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.gate.ISystemGate;
import michael.linker.gestureid.data.system.gate.ISystemGateListener;
import michael.linker.gestureid.data.system.gate.SystemGateAuthResult;

public class SystemGateConfigurationBean
        extends ConfigurationBean<ISystemGate>
        implements ISystemGate {
    public SystemGateConfigurationBean(ISystemGate defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public ISystemGate implement(ISystemGate newImplementation) {
        setImplementation(newImplementation);
        return getImplementation();
    }

    @Override
    public void notifyAboutAuthResult(SystemGateAuthResult authResult) {
        getImplementation().notifyAboutAuthResult(authResult);
    }

    @Override
    public void shutdown() {
        getImplementation().shutdown();
    }

    @Override
    public void subscribe(ISystemGateListener listener) {
        getImplementation().subscribe(listener);
    }

    @Override
    public void unsubscribe() {
        getImplementation().unsubscribe();
    }

    @Override
    public void notifyAboutEpisode(AccumulatedEpisode accumulatedEpisode) {
        getImplementation().notifyAboutEpisode(accumulatedEpisode);
    }
}
