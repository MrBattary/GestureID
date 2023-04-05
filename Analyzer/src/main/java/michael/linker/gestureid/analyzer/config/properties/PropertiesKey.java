package michael.linker.gestureid.analyzer.config.properties;

public enum PropertiesKey {
    IS_ACCELEROMETER_METRIC_CLASS_REQUIRED_KEY("metric.class.isAccelerometerRequired"),
    IS_GYROSCOPE_METRIC_CLASS_REQUIRED_KEY("metric.class.isGyroscopeRequired"),
    IS_MAGNETOMETER_METRIC_CLASS_REQUIRED_KEY("metric.class.isMagnetometerRequired"),
    IS_GRAVITY_METRIC_CLASS_REQUIRED_KEY("metric.class.isGravityRequired"),
    IS_LINEAR_ACCELERATION_METRIC_CLASS_REQUIRED_KEY("metric.class.isLinearAccelerationRequired"),
    IS_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY("metric.class.isRotationVectorRequired"),
    IS_GEO_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY("metric.class.isGeoRotationVectorRequired");
    private final String propertyKey;

    PropertiesKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    @Override
    public String toString() {
        return propertyKey;
    }
}
