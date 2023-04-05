package michael.linker.gestureid.analyzer.config;

import michael.linker.gestureid.analyzer.config.properties.PropertiesKey;
import michael.linker.gestureid.analyzer.config.properties.PropertiesProvider;

public class StreamConfiguration {
    public static String getSourceDirectoryPath() {
        return PropertiesProvider.getStringProperty(PropertiesKey.TEST_RESULT_DIRECTORY);
    }
}
