package michael.linker.gestureid.analyzer.config;

import michael.linker.gestureid.analyzer.config.properties.PropertiesKey;
import michael.linker.gestureid.analyzer.config.properties.PropertiesProvider;

public class FileConfiguration {
    public static String getResultsSourceDirectoryPath() {
        return PropertiesProvider.getStringProperty(PropertiesKey.RESULTS_SOURCE_DIRECTORY_PATH);
    }
}
