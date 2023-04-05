package michael.linker.gestureid.analyzer.config.properties;

public enum PropertiesKey {
    IS_ACCELEROMETER_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isAccelerometerRequired"),
    IS_GYROSCOPE_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isGyroscopeRequired"),
    IS_MAGNETOMETER_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isMagnetometerRequired"),
    IS_GRAVITY_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isGravityRequired"),
    IS_LINEAR_ACCELERATION_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isLinearAccelerationRequired"),
    IS_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isRotationVectorRequired"),
    IS_GEO_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isGeoRotationVectorRequired"),
    TEST_RESULT_DIRECTORY("analyzer.directory.source.path");

    private final String propertyKey;

    PropertiesKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    @Override
    public String toString() {
        return propertyKey;
    }
}
