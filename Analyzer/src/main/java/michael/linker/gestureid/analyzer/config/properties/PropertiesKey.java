package michael.linker.gestureid.analyzer.config.properties;

public enum PropertiesKey {
    IS_ACCELEROMETER_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isAccelerometerRequired"),
    IS_GYROSCOPE_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isGyroscopeRequired"),
    IS_MAGNETOMETER_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isMagnetometerRequired"),
    IS_GRAVITY_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isGravityRequired"),
    IS_LINEAR_ACCELERATION_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isLinearAccelerationRequired"),
    IS_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isRotationVectorRequired"),
    IS_GEO_ROTATION_VECTOR_METRIC_CLASS_REQUIRED_KEY("analyzer.metric.class.isGeoRotationVectorRequired"),
    RESULTS_SOURCE_DIRECTORY_PATH("analyzer.source.directory.path"),
    RESULTS_DESTINATION_DIRECTORY_PATH("analyzer.destination.directory.path"),
    RESULTS_DESTINATION_FILE_EXTENSION("analyzer.destination.file.extension"),
    USER_MODEL_MARK("analyzer.user.model.mark"),
    ANALYSIS_CALCULATION_AMOUNT_TYPE("analyzer.calculation.amount.type"),
    ANALYSIS_CALCULATION_DISPERSION_TYPE("analyzer.calculation.intersection.type"),
    ANALYSIS_CALCULATION_DISPERSIONS("analyzer.calculation.intersection.dispersions"),
    ANALYSIS_CALCULATION_FAR_TYPE("analyzer.calculation.far.type"),
    ANALYSIS_CALCULATION_FAR_DISPERSIONS("analyzer.calculation.far.dispersions"),
    ANALYSIS_CALCULATION_INTERSECTOR_MODE_FIRST_MATCH("analyzer.calculation.intersector.mode.firstMatch"),
    ANALYSIS_CALCULATION_INTERSECTOR_MODE_FULL("analyzer.calculation.intersector.mode.full");

    private final String propertyKey;

    PropertiesKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    @Override
    public String toString() {
        return propertyKey;
    }
}
