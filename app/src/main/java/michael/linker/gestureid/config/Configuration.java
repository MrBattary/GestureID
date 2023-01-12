package michael.linker.gestureid.config;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.config.event.EventSynchronizerConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerManagerConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerProviderConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerSuppressorConfiguration;
import michael.linker.gestureid.config.sensor.SensorManagerConfiguration;
import michael.linker.gestureid.config.sensor.SensorProviderConfiguration;

public class Configuration {
    private static Map<ConfigurationType, String> currentConfigurationMap;

    static {
        currentConfigurationMap = new HashMap<>();
    }

    public static void updateConfiguration(ConfigurationChain chain) {
        currentConfigurationMap = chain.getCurrentConfigurationMap();
        SensorProviderConfiguration.configure();
        SensorListenerSuppressorConfiguration.configure();
        SensorListenerProviderConfiguration.configure();
        EventAccumulatorConfiguration.configure();
        EventSynchronizerConfiguration.configure();
        SensorListenerManagerConfiguration.configure();
        SensorManagerConfiguration.configure();
    }

    public static String getConfiguration(ConfigurationType configurationType) {
        return currentConfigurationMap.get(configurationType);
    }

    public static ConfigurationChain getCurrentConfiguration() {
        return new ConfigurationChain(currentConfigurationMap);
    }
}
