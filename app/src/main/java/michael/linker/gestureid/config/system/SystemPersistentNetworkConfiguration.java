package michael.linker.gestureid.config.system;

import michael.linker.gestureid.config.Configuration;
import michael.linker.gestureid.config.ConfigurationType;
import michael.linker.gestureid.config.IConfiguration;
import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.config.system.bean.SystemPersistentNetworkConfigurationBean;
import michael.linker.gestureid.data.system.network.DatabaseSystemNetwork;
import michael.linker.gestureid.data.system.network.IPersistentSystemNetwork;
import michael.linker.gestureid.data.system.network.LocalSystemNetwork;

/**
 * Provides configuration of persistent network
 */
public class SystemPersistentNetworkConfiguration implements IConfiguration {
    private static IPersistentSystemNetwork dbSystemNetwork = null;
    private static ConfigurationBean<IPersistentSystemNetwork>
            persistentNetworkConfigurationBean = null;

    public static IPersistentSystemNetwork getPersistentNetwork() {
        if (persistentNetworkConfigurationBean == null) {
            persistentNetworkConfigurationBean =
                    new SystemPersistentNetworkConfigurationBean(createImplementation(
                            SystemConfiguration.Build.Network.getPersistentNetworkType()));
        }
        return (IPersistentSystemNetwork) persistentNetworkConfigurationBean;
    }

    @Override
    public void configure() {
        persistentNetworkConfigurationBean =
                new SystemPersistentNetworkConfigurationBean(createImplementation(
                        SystemConfiguration.Type.PersistentNetwork.valueOf(Configuration.getConfiguration(
                                ConfigurationType.SYSTEM_PERSISTENT_NETWORK))));
    }

    private static IPersistentSystemNetwork createImplementation(
            SystemConfiguration.Type.PersistentNetwork persistentNetworkType) {
        switch (persistentNetworkType) {
            case DATABASE:
                if (dbSystemNetwork == null) {
                    dbSystemNetwork = new DatabaseSystemNetwork();
                }
                return dbSystemNetwork;
            case LOCAL:
            default:
                return new LocalSystemNetwork();
        }
    }
}
