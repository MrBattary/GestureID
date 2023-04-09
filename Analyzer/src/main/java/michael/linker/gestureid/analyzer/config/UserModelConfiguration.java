package michael.linker.gestureid.analyzer.config;

import michael.linker.gestureid.analyzer.config.properties.PropertiesKey;
import michael.linker.gestureid.analyzer.config.properties.PropertiesProvider;

public class UserModelConfiguration {
    public static String getUserModelMark() {
        return PropertiesProvider.getStringProperty(PropertiesKey.USER_MODEL_MARK);
    }
}
