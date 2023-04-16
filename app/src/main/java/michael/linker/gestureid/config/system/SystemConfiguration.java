package michael.linker.gestureid.config.system;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import michael.linker.gestureid.BuildConfig;
import michael.linker.gestureid.config.sensor.SensorsConfiguration;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;
import michael.linker.gestureid.data.system.metric.type.MetricType;

public final class SystemConfiguration {
    public static final class Type {
        public enum Status {
            ENABLED,
            DISABLED
        }
        public enum PersistentNetwork {
            DATABASE,
            LOCAL
        }
    }

    public static final class Build {
        public static String getBuildHash() {
            StringBuilder sb = new StringBuilder();
            for (MetricClassType classType : MetricClassType.values()) {

                if (Class.isMetricClassRequired(classType)) {
                    sb.append(classType);
                    Map<MetricGroupType, SortedSet<MetricType>> metricsOfClass =
                            Metric.getMetricsForClass(classType);
                    SortedSet<MetricGroupType> metricGroupTypeSet = new TreeSet<>(
                            metricsOfClass.keySet());

                    for (MetricGroupType metricGroupType : metricGroupTypeSet) {
                        sb.append(metricGroupType);
                        SortedSet<MetricType> metricTypeSet = metricsOfClass.get(metricGroupType);
                        assert metricTypeSet != null;

                        for (MetricType metricType : metricTypeSet) {
                            sb.append(metricType);
                        }
                    }
                }
            }
            return sb.toString();
        }

        public static final class Class {
            public static boolean isMetricClassRequired(MetricClassType classType) {
                switch (classType) {
                    case GENERAL_METRIC_CLASS:
                        return true;
                    case ACCELEROMETER_METRIC_CLASS:
                        return isAccelerometerMetricClassRequired();
                    case GYROSCOPE_METRIC_CLASS:
                        return isGyroscopeMetricClassRequired();
                    case MAGNETOMETER_METRIC_CLASS:
                        return isMagnetometerMetricClassRequired();
                    case GRAVITY_METRIC_CLASS:
                        return isGravityMetricClassRequired();
                    case LINEAR_ACCELERATION_METRIC_CLASS:
                        return isLinearAccelerationMetricClassRequired();
                    case ROTATION_VECTOR_METRIC_CLASS:
                        return isRotationVectorMetricClassRequired();
                    case GEO_ROTATION_VECTOR_METRIC_CLASS:
                        return isGeoRotationVectorMetricClassRequired();
                    default:
                        return false;
                }
            }

            public static boolean isAccelerometerMetricClassRequired() {
                return !SensorsConfiguration.Build.isAccelerometerDeactivated();
            }

            public static boolean isGyroscopeMetricClassRequired() {
                return !SensorsConfiguration.Build.isGyroscopeDeactivated();
            }

            public static boolean isMagnetometerMetricClassRequired() {
                return !SensorsConfiguration.Build.isMagnetometerDeactivated();
            }

            public static boolean isGravityMetricClassRequired() {
                return !SensorsConfiguration.Build.isGravityDeactivated();
            }

            public static boolean isLinearAccelerationMetricClassRequired() {
                return !SensorsConfiguration.Build.isLinearAccelerationDeactivated();
            }

            public static boolean isRotationVectorMetricClassRequired() {
                return !SensorsConfiguration.Build.isRotationVectorDeactivated();
            }

            public static boolean isGeoRotationVectorMetricClassRequired() {
                return !SensorsConfiguration.Build.isGeomagneticRotationVectorDeactivated();
            }
        }

        public static final class Metric {
            private static final Map<MetricGroupType, SortedSet<MetricType>> generalMetrics,
                    accelerometerMetrics, gyroscopeMetrics, magnetometerMetrics, gravityMetrics,
                    linearAccelerationMetrics, rotationVectorMetrics, geoRotationVectorMetrics;

            static {
                generalMetrics = Map.of(MetricGroupType.TIME_METRIC_GROUP, getGeneralMetrics());
                accelerometerMetrics = Map.of(
                        MetricGroupType.AXIS_X_METRIC_GROUP, getAccelerometerXMetrics(),
                        MetricGroupType.AXIS_Y_METRIC_GROUP, getAccelerometerYMetrics(),
                        MetricGroupType.AXIS_Z_METRIC_GROUP, getAccelerometerZMetrics());
                gyroscopeMetrics = Map.of(
                        MetricGroupType.AXIS_X_METRIC_GROUP, getGyroscopeXMetrics(),
                        MetricGroupType.AXIS_Y_METRIC_GROUP, getGyroscopeYMetrics(),
                        MetricGroupType.AXIS_Z_METRIC_GROUP, getGyroscopeZMetrics());
                magnetometerMetrics = Map.of(
                        MetricGroupType.AXIS_X_METRIC_GROUP, getMagnetometerXMetrics(),
                        MetricGroupType.AXIS_Y_METRIC_GROUP, getMagnetometerYMetrics(),
                        MetricGroupType.AXIS_Z_METRIC_GROUP, getMagnetometerZMetrics());
                gravityMetrics = Map.of(
                        MetricGroupType.AXIS_X_METRIC_GROUP, getGravityXMetrics(),
                        MetricGroupType.AXIS_Y_METRIC_GROUP, getGravityYMetrics(),
                        MetricGroupType.AXIS_Z_METRIC_GROUP, getGravityZMetrics());
                linearAccelerationMetrics = Map.of(
                        MetricGroupType.AXIS_X_METRIC_GROUP, getLinearAccelerationXMetrics(),
                        MetricGroupType.AXIS_Y_METRIC_GROUP, getLinearAccelerationYMetrics(),
                        MetricGroupType.AXIS_Z_METRIC_GROUP, getLinearAccelerationZMetrics());
                rotationVectorMetrics = Map.of(
                        MetricGroupType.AXIS_X_METRIC_GROUP, getRotationVectorXMetrics(),
                        MetricGroupType.AXIS_Y_METRIC_GROUP, getRotationVectorYMetrics(),
                        MetricGroupType.AXIS_Z_METRIC_GROUP, getRotationVectorZMetrics(),
                        MetricGroupType.AXIS_W_METRIC_GROUP, getRotationVectorWMetrics());
                geoRotationVectorMetrics = Map.of(
                        MetricGroupType.AXIS_X_METRIC_GROUP, getGeoRotationVectorXMetrics(),
                        MetricGroupType.AXIS_Y_METRIC_GROUP, getGeoRotationVectorYMetrics(),
                        MetricGroupType.AXIS_Z_METRIC_GROUP, getGeoRotationVectorZMetrics(),
                        MetricGroupType.AXIS_W_METRIC_GROUP,
                        getGeoRotationVectorWMetrics());

            }

            public static Map<MetricGroupType, SortedSet<MetricType>> getMetricsForClass(
                    MetricClassType classType) {
                switch (classType) {
                    case GENERAL_METRIC_CLASS:
                        return generalMetrics;
                    case ACCELEROMETER_METRIC_CLASS:
                        return accelerometerMetrics;
                    case GYROSCOPE_METRIC_CLASS:
                        return gyroscopeMetrics;
                    case MAGNETOMETER_METRIC_CLASS:
                        return magnetometerMetrics;
                    case GRAVITY_METRIC_CLASS:
                        return gravityMetrics;
                    case LINEAR_ACCELERATION_METRIC_CLASS:
                        return linearAccelerationMetrics;
                    case ROTATION_VECTOR_METRIC_CLASS:
                        return rotationVectorMetrics;
                    case GEO_ROTATION_VECTOR_METRIC_CLASS:
                        return geoRotationVectorMetrics;
                    default:
                        return new HashMap<>();
                }
            }

            public static SortedSet<MetricType> getGeneralMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GENERAL_TIME));
            }

            public static SortedSet<MetricType> getAccelerometerXMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.ACCELEROMETER_X));
            }

            public static SortedSet<MetricType> getAccelerometerYMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.ACCELEROMETER_Y));
            }

            public static SortedSet<MetricType> getAccelerometerZMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.ACCELEROMETER_Z));
            }

            public static SortedSet<MetricType> getGyroscopeXMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GYROSCOPE_X));
            }

            public static SortedSet<MetricType> getGyroscopeYMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GYROSCOPE_Y));
            }

            public static SortedSet<MetricType> getGyroscopeZMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GYROSCOPE_Z));
            }

            public static SortedSet<MetricType> getMagnetometerXMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.MAGNETOMETER_X));
            }

            public static SortedSet<MetricType> getMagnetometerYMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.MAGNETOMETER_Y));
            }

            public static SortedSet<MetricType> getMagnetometerZMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.MAGNETOMETER_Z));
            }

            public static SortedSet<MetricType> getGravityXMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GRAVITY_X));
            }

            public static SortedSet<MetricType> getGravityYMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GRAVITY_Y));
            }

            public static SortedSet<MetricType> getGravityZMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GRAVITY_Z));
            }

            public static SortedSet<MetricType> getLinearAccelerationXMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.LINEAR_ACCELERATION_X));
            }

            public static SortedSet<MetricType> getLinearAccelerationYMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.LINEAR_ACCELERATION_Y));
            }

            public static SortedSet<MetricType> getLinearAccelerationZMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.LINEAR_ACCELERATION_Z));
            }

            public static SortedSet<MetricType> getRotationVectorXMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.ROTATION_VECTOR_X));
            }

            public static SortedSet<MetricType> getRotationVectorYMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.ROTATION_VECTOR_Y));
            }

            public static SortedSet<MetricType> getRotationVectorZMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.ROTATION_VECTOR_Z));
            }

            public static SortedSet<MetricType> getRotationVectorWMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.ROTATION_VECTOR_W));
            }

            public static SortedSet<MetricType> getGeoRotationVectorXMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GEO_ROTATION_VECTOR_X));
            }

            public static SortedSet<MetricType> getGeoRotationVectorYMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GEO_ROTATION_VECTOR_Y));
            }

            public static SortedSet<MetricType> getGeoRotationVectorZMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GEO_ROTATION_VECTOR_Z));
            }

            public static SortedSet<MetricType> getGeoRotationVectorWMetrics() {
                return new TreeSet<>(Set.of(BuildConfig.GEO_ROTATION_VECTOR_W));
            }
        }

        public static final class Network {
            public static Type.PersistentNetwork getPersistentNetworkType() {
                return BuildConfig.PERSISITENT_NETWORK_TYPE;
            }

            public static Type.PersistentNetwork getPersistentNetworkTypeDuringTest() {
                return BuildConfig.PERSISITENT_NETWORK_TYPE_DURING_TEST;
            }

            public static int getNumberOfUnrecognizedEpisodes() {
                return BuildConfig.NUMBER_OF_UNRECOGNIZED_EPISODES;
            }

            public static double getAcceptableSpread() {
                return BuildConfig.ACCEPTABLE_SPREAD;
            }

            public static boolean shouldUpdateOnAccept() {
                return BuildConfig.SHOULD_UPDATE_ON_ACCEPT;
            }
        }

        public static final class Benchmark {
            public static boolean shouldCalculateFrrBenchmark() {
                return BuildConfig.SHOULD_CALCULATE_FRR_BENCHMARK;
            }
        }
    }
}
