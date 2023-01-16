package michael.linker.gestureid.config.system;

import android.util.ArraySet;

import java.util.Set;

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
    }

    public static final class Build {
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

        public static final class Group {
            public static Set<MetricGroupType> getMetricGroupForClass(MetricClassType classType) {
                switch (classType) {
                    case GENERAL_METRIC_CLASS:
                        return getMetricGroupForGeneralClass();
                    case ACCELEROMETER_METRIC_CLASS:
                        return getMetricGroupForAccelerometerClass();
                    case GYROSCOPE_METRIC_CLASS:
                        return getMetricGroupForGyroscopeClass();
                    case MAGNETOMETER_METRIC_CLASS:
                        return getMetricGroupForMagnetometerClass();
                    case GRAVITY_METRIC_CLASS:
                        return getMetricGroupForGravityClass();
                    case LINEAR_ACCELERATION_METRIC_CLASS:
                        return getMetricLinearAccelerationClass();
                    case ROTATION_VECTOR_METRIC_CLASS:
                        return getMetricGroupForRotationVectorClass();
                    case GEO_ROTATION_VECTOR_METRIC_CLASS:
                        return getMetricGroupForGeoRotationVectorClass();
                    default:
                        return new ArraySet<>();
                }
            }

            public static Set<MetricGroupType> getMetricGroupForGeneralClass() {
                return Set.of(MetricGroupType.TIME_METRIC_GROUP);
            }

            public static Set<MetricGroupType> getMetricGroupForAccelerometerClass() {
                return Set.of(MetricGroupType.AXIS_X_METRIC_GROUP,
                        MetricGroupType.AXIS_Y_METRIC_GROUP,
                        MetricGroupType.AXIS_Z_METRIC_GROUP);
            }

            public static Set<MetricGroupType> getMetricGroupForGyroscopeClass() {
                return Set.of(MetricGroupType.AXIS_X_METRIC_GROUP,
                        MetricGroupType.AXIS_Y_METRIC_GROUP,
                        MetricGroupType.AXIS_Z_METRIC_GROUP);
            }


            public static Set<MetricGroupType> getMetricGroupForMagnetometerClass() {
                return Set.of(MetricGroupType.AXIS_STATIC_X_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_Y_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_Z_METRIC_GROUP);
            }

            public static Set<MetricGroupType> getMetricGroupForGravityClass() {
                return Set.of(MetricGroupType.AXIS_STATIC_X_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_Y_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_Z_METRIC_GROUP);
            }

            public static Set<MetricGroupType> getMetricLinearAccelerationClass() {
                return Set.of(MetricGroupType.AXIS_X_METRIC_GROUP,
                        MetricGroupType.AXIS_Y_METRIC_GROUP,
                        MetricGroupType.AXIS_Z_METRIC_GROUP);
            }

            public static Set<MetricGroupType> getMetricGroupForRotationVectorClass() {
                return Set.of(MetricGroupType.AXIS_STATIC_X_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_Y_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_Z_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_W_METRIC_GROUP);
            }

            public static Set<MetricGroupType> getMetricGroupForGeoRotationVectorClass() {
                return Set.of(MetricGroupType.AXIS_STATIC_X_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_Y_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_Z_METRIC_GROUP,
                        MetricGroupType.AXIS_STATIC_W_METRIC_GROUP);
            }
        }

        public static final class Metric {
            public static Set<MetricType> getMetricForGroup(MetricGroupType metricGroupType) {
                switch (metricGroupType) {
                    case TIME_METRIC_GROUP:
                        return getMetricForTimeGroup();
                    case AXIS_X_METRIC_GROUP:
                        return getMetricForAxisXGroup();
                    case AXIS_Y_METRIC_GROUP:
                        return getMetricForAxisYGroup();
                    case AXIS_Z_METRIC_GROUP:
                        return getMetricForAxisZGroup();
                    case AXIS_W_METRIC_GROUP:
                        return getMetricForAxisWGroup();
                    case AXIS_STATIC_X_METRIC_GROUP:
                        return getMetricForStaticAxisXGroup();
                    case AXIS_STATIC_Y_METRIC_GROUP:
                        return getMetricForStaticAxisYGroup();
                    case AXIS_STATIC_Z_METRIC_GROUP:
                        return getMetricForStaticAxisZGroup();
                    case AXIS_STATIC_W_METRIC_GROUP:
                        return getMetricForStaticAxisWGroup();
                    default:
                        return new ArraySet<>();
                }
            }

            public static Set<MetricType> getMetricForTimeGroup() {
                return Set.of(MetricType.SPREAD_METRIC);
            }

            public static Set<MetricType> getMetricForAxisXGroup() {
                return Set.of(MetricType.MIN_METRIC, MetricType.MAX_METRIC,
                        MetricType.SPREAD_METRIC);
            }

            public static Set<MetricType> getMetricForAxisYGroup() {
                return Set.of(MetricType.MIN_METRIC, MetricType.MAX_METRIC,
                        MetricType.SPREAD_METRIC);
            }

            public static Set<MetricType> getMetricForAxisZGroup() {
                return Set.of(MetricType.MIN_METRIC, MetricType.MAX_METRIC,
                        MetricType.SPREAD_METRIC);
            }

            public static Set<MetricType> getMetricForAxisWGroup() {
                return Set.of(MetricType.MIN_METRIC, MetricType.MAX_METRIC,
                        MetricType.SPREAD_METRIC);
            }

            public static Set<MetricType> getMetricForStaticAxisXGroup() {
                return Set.of(MetricType.ARITHMETIC_MEAN);
            }

            public static Set<MetricType> getMetricForStaticAxisYGroup() {
                return Set.of(MetricType.ARITHMETIC_MEAN);
            }

            public static Set<MetricType> getMetricForStaticAxisZGroup() {
                return Set.of(MetricType.ARITHMETIC_MEAN);
            }

            public static Set<MetricType> getMetricForStaticAxisWGroup() {
                return Set.of(MetricType.ARITHMETIC_MEAN);
            }
        }

        public static final class Network {
            public static int getNumberOfUnrecognizedEpisodes() {
                return 3;
            }

            public static double getAcceptableSpread() {
                return 0.1;
            }
        }
    }
}
