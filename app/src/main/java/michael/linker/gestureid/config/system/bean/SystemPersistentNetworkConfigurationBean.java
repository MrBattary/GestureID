package michael.linker.gestureid.config.system.bean;

import java.util.List;

import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.network.IPersistentSystemNetwork;
import michael.linker.gestureid.data.system.network.type.SystemNetworkResult;

public class SystemPersistentNetworkConfigurationBean
        extends ConfigurationBean<IPersistentSystemNetwork>
        implements IPersistentSystemNetwork {
    public SystemPersistentNetworkConfigurationBean(
            IPersistentSystemNetwork defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public IPersistentSystemNetwork implement(IPersistentSystemNetwork newImplementation) {
        super.setImplementation(newImplementation);
        return getImplementation();
    }

    @Override
    public void persist() {
        getImplementation().persist();
    }

    @Override
    public SystemNetworkResult proceed(EpisodeMetrics metrics) {
        return getImplementation().proceed(metrics);
    }

    @Override
    public void create(EpisodeMetrics metrics) {
        getImplementation().create(metrics);
    }

    @Override
    public List<EpisodeMetrics> getNodes() {
        return getImplementation().getNodes();
    }

    @Override
    public void purgeNodes() {
        getImplementation().purgeNodes();
    }
}
