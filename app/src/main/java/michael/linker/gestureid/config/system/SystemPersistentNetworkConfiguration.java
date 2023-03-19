package michael.linker.gestureid.config.system;

import michael.linker.gestureid.data.system.network.DatabaseSystemNetwork;
import michael.linker.gestureid.data.system.network.IPersistentSystemNetwork;
import michael.linker.gestureid.data.system.network.LocalSystemNetwork;
import michael.linker.gestureid.data.system.network.type.SystemNetworkType;

/**
 * Provides configuration of persistent network
 */
public class SystemPersistentNetworkConfiguration {
    private static SystemNetworkType storageType;

    static {
        resetStorageTypeFromConfiguration();
    }

    public static void setStorageType(SystemNetworkType storageType) {
        SystemPersistentNetworkConfiguration.storageType = storageType;
    }

    public static void resetStorageTypeFromConfiguration() {
        storageType = SystemConfiguration.Build.Network.getSystemPersistentNetworkType();
    }

    public static IPersistentSystemNetwork getFreshPersistentNetwork() {
        switch (storageType) {
            case DATABASE:
                return new DatabaseSystemNetwork();
            case LOCAL:
            default:
                return new LocalSystemNetwork();
        }
    }
}
