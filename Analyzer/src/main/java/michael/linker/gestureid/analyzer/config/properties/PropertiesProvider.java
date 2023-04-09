package michael.linker.gestureid.analyzer.config.properties;

import michael.linker.gestureid.analyzer.config.properties.exception.PropertiesNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Provider of application properties.
 */
public class PropertiesProvider {
    private static final String PROPERTIES_FILENAME = "application.properties";
    private static final String PROPERTIES_LIST_SPLITTER = ",";
    private static final Properties PROPERTIES = PropertiesLoader.loadProperties(PROPERTIES_FILENAME);

    public static Long getLongProperty(PropertiesKey key) throws PropertiesNotFoundException {
        try {
            return Long.valueOf(PROPERTIES.getProperty(key.toString()));
        } catch (NumberFormatException e) {
            throw new PropertiesNotFoundException(key.toString(), e);
        }
    }

    public static List<Long> getLongListProperty(PropertiesKey key) throws PropertiesNotFoundException {
        List<String> stringList = new ArrayList<>(Arrays.asList(PROPERTIES.getProperty(key.toString())
                .split(PROPERTIES_LIST_SPLITTER)));
        return stringList.stream()
                .map(id -> {
                    try {
                        return Long.valueOf(id);
                    } catch (NumberFormatException e) {
                        throw new PropertiesNotFoundException(key.toString(), e);
                    }
                })
                .collect(Collectors.toList());
    }

    public static List<Double> getDoubleListProperty(PropertiesKey key) throws PropertiesNotFoundException {
        List<String> stringList = new ArrayList<>(Arrays.asList(PROPERTIES.getProperty(key.toString())
                .split(PROPERTIES_LIST_SPLITTER)));
        return stringList.stream()
                .map(id -> {
                    try {
                        return Double.valueOf(id);
                    } catch (NumberFormatException e) {
                        throw new PropertiesNotFoundException(key.toString(), e);
                    }
                })
                .collect(Collectors.toList());
    }

    public static String getStringProperty(PropertiesKey key) throws PropertiesNotFoundException {
        String string = PROPERTIES.getProperty(key.toString(), null);
        if (string == null) {
            throw new PropertiesNotFoundException(key.toString());
        }
        return string;
    }

    public static Boolean getBooleanProperty(PropertiesKey key) throws PropertiesNotFoundException {
        String stringBool = PROPERTIES.getProperty(key.toString()).toLowerCase();
        if (!stringBool.equals(Boolean.FALSE.toString().toLowerCase()) &&
                !stringBool.equals(Boolean.TRUE.toString().toLowerCase())) {
            throw new PropertiesNotFoundException(key.toString());
        }
        return Boolean.valueOf(stringBool);
    }
}
