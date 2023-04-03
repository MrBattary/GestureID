package michael.linker.gestureid.analyzer.config.properties;

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
    // Keys
    // TODO: To the enum
    private static final String IS_ACCELEROMETER_METRIC_CLASS_REQUIRED_KEY =
            "metric.class.isAccelerometerRequired";
    private static final String IS_GYROSCOPE_METRIC_CLASS_REQUIRED_KEY =
            "metric.class.isGyroscopeRequired";
    private static final String IS_MAGNETOMETER_METRIC_CLASS_REQUIRED_KEY =
            "metric.class.isMagnetometerRequired";
    private static final String IS_GRAVITY_METRIC_CLASS_REQUIRED_KEY =
            "metric.class.isGravityRequired";
    private static final String IS_LINEAR_ACCELERATION_METRIC_CLASS_REQUIRED_KEY =
            "metric.class.isLinearAccelerationRequired";
    private static final String IS_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY =
            "metric.class.isRotationVectorRequired";
    private static final String IS_GEO_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY =
            "metric.class.isGeoRotationVectorRequired";

    // Values
    private final Boolean isAccelerometerRequired, isGyroscopeRequired, isMagnetometerRequired, isGravityRequired,
            isLinearAccelerationRequired, isRotationVectorRequired, isGeoRotationVectorRequired;

    // TODO: Make static
    /**
     * Default constructor.
     *
     * @throws PropertiesNotAvailableException if .properties file was not available.
     * @throws PropertiesNotFoundException     if any required property was not found.
     */
    public PropertiesProvider() throws PropertiesNotAvailableException, PropertiesNotFoundException {
        Properties properties = PropertiesLoader.loadProperties(PROPERTIES_FILENAME);
        isAccelerometerRequired = getBooleanProperty(properties, IS_ACCELEROMETER_METRIC_CLASS_REQUIRED_KEY);
        isGyroscopeRequired = getBooleanProperty(properties, IS_GYROSCOPE_METRIC_CLASS_REQUIRED_KEY);
        isMagnetometerRequired = getBooleanProperty(properties, IS_MAGNETOMETER_METRIC_CLASS_REQUIRED_KEY);
        isGravityRequired = getBooleanProperty(properties, IS_GRAVITY_METRIC_CLASS_REQUIRED_KEY);
        isLinearAccelerationRequired = getBooleanProperty(properties, IS_LINEAR_ACCELERATION_METRIC_CLASS_REQUIRED_KEY);
        isRotationVectorRequired = getBooleanProperty(properties, IS_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY);
        isGeoRotationVectorRequired = getBooleanProperty(properties, IS_GEO_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY);
    }

    private Long getLongProperty(Properties properties, String key) throws PropertiesNotFoundException {
        try {
            return Long.valueOf(properties.getProperty(key));
        } catch (NumberFormatException e) {
            throw new PropertiesNotFoundException(key, e);
        }
    }

    private List<Long> getLongListProperty(Properties properties, String key) throws PropertiesNotFoundException {
        List<String> stringList = new ArrayList<>(Arrays.asList(properties.getProperty(key)
                .split(PROPERTIES_LIST_SPLITTER)));
        return stringList.stream()
                .map(id -> {
                    try {
                        return Long.valueOf(id);
                    } catch (NumberFormatException e) {
                        throw new PropertiesNotFoundException(key, e);
                    }
                })
                .collect(Collectors.toList());
    }

    private Boolean getBooleanProperty(Properties properties, String key) throws PropertiesNotFoundException {
        String stringBool = properties.getProperty(key).toLowerCase();
        if (!stringBool.equals(Boolean.FALSE.toString().toLowerCase()) &&
                !stringBool.equals(Boolean.TRUE.toString().toLowerCase())) {
            throw new PropertiesNotFoundException(key);
        }
        return Boolean.valueOf(stringBool);
    }
}
