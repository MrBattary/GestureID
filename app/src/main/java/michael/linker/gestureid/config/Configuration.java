package michael.linker.gestureid.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.config.event.EventSynchronizerConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerManagerConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerProviderConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerSuppressorConfiguration;
import michael.linker.gestureid.config.sensor.SensorManagerConfiguration;
import michael.linker.gestureid.config.sensor.SensorProviderConfiguration;
import michael.linker.gestureid.config.system.SystemGateConfiguration;
import michael.linker.gestureid.config.system.SystemPersistentNetworkConfiguration;

public final class Configuration {
    private static final List<IConfiguration> CONFIGURATION_LIST;
    private static Map<ConfigurationType, String> currentConfigurationMap;

    static {
        currentConfigurationMap = new HashMap<>();
        CONFIGURATION_LIST = new ArrayList<>();
        // ORDER IS IMPORTANT!
        CONFIGURATION_LIST.add(new SensorProviderConfiguration());
        CONFIGURATION_LIST.add(new SensorListenerSuppressorConfiguration());
        CONFIGURATION_LIST.add(new SensorListenerProviderConfiguration());
        CONFIGURATION_LIST.add(new EventAccumulatorConfiguration());
        CONFIGURATION_LIST.add(new EventSynchronizerConfiguration());
        CONFIGURATION_LIST.add(new SensorListenerManagerConfiguration());
        CONFIGURATION_LIST.add(new SensorManagerConfiguration());
        CONFIGURATION_LIST.add(new SystemPersistentNetworkConfiguration());
        CONFIGURATION_LIST.add(new SystemGateConfiguration());
    }

    public static void updateConfiguration(ConfigurationChain chain) {
        currentConfigurationMap = chain.getCurrentConfigurationMap();
        for (IConfiguration configuration : CONFIGURATION_LIST) {
            configuration.configure();
        }
    }

    public static String getConfiguration(ConfigurationType configurationType) {
        return currentConfigurationMap.get(configurationType);
    }

    public static ConfigurationChain getCurrentConfiguration() {
        return new ConfigurationChain(currentConfigurationMap);
    }
}
