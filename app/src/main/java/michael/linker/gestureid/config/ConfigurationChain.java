package michael.linker.gestureid.config;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.config.system.SystemConfiguration;

public final class ConfigurationChain {
    private static final String DEFAULT_TYPE = "DEFAULT";
    private final Map<ConfigurationType, String> currentConfigurationMap;

    public ConfigurationChain(
            EventAccumulatorConfiguration.Type eventAccumulatorType,
            SystemConfiguration.Type.Status systemStatusType,
            SystemConfiguration.Type.PersistentNetwork persistentPersistentNetworkType) {
        currentConfigurationMap = new HashMap<>();
        currentConfigurationMap.put(ConfigurationType.EVENT_ACCUMULATOR,
                eventAccumulatorType.toString());
        currentConfigurationMap.put(ConfigurationType.EVENT_SYNCHRONIZER, DEFAULT_TYPE);
        currentConfigurationMap.put(ConfigurationType.SENSOR_LISTENER, DEFAULT_TYPE);
        currentConfigurationMap.put(ConfigurationType.SENSOR_LISTENER_PROVIDER, DEFAULT_TYPE);
        currentConfigurationMap.put(ConfigurationType.SENSOR_LISTENER_MANAGER, DEFAULT_TYPE);
        currentConfigurationMap.put(ConfigurationType.SENSOR_PROVIDER, DEFAULT_TYPE);
        currentConfigurationMap.put(ConfigurationType.SENSOR_MANAGER, DEFAULT_TYPE);
        currentConfigurationMap.put(ConfigurationType.SYSTEM_GATE, systemStatusType.toString());
        currentConfigurationMap.put(ConfigurationType.SYSTEM_PERSISTENT_NETWORK,
                persistentPersistentNetworkType.toString());
    }

    public ConfigurationChain(Map<ConfigurationType, String> currentConfiguration) {
        currentConfigurationMap = currentConfiguration;
    }

    public Map<ConfigurationType, String> getCurrentConfigurationMap() {
        return currentConfigurationMap;
    }
}
