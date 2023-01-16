package michael.linker.gestureid.config.system;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
        public static String getBuildHash() {
            StringBuilder sb = new StringBuilder();
            for (MetricClassType classType : MetricClassType.values()) {
                if (Class.isMetricClassRequired(classType)) {
                    sb.append(classType);
                }
                for (MetricGroupType groupType : Group.getMetricGroupForClass(classType)) {
                    sb.append(groupType);
                }
            }
            for (MetricGroupType groupType : MetricGroupType.values()) {
                sb.append(groupType);
                for (MetricType metricType : Metric.getMetricForGroup(groupType)) {
                    sb.append(metricType);
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

        public static final class Group {
            private static final SortedSet<MetricGroupType> GENERAL_METRIC_CLASS_SET;
            private static final SortedSet<MetricGroupType> ACCELEROMETER_METRIC_CLASS_SET;
            private static final SortedSet<MetricGroupType> GYROSCOPE_METRIC_CLASS_SET;
            private static final SortedSet<MetricGroupType> MAGNETOMETER_METRIC_CLASS_SET;
            private static final SortedSet<MetricGroupType> GRAVITY_METRIC_CLASS_SET;
            private static final SortedSet<MetricGroupType> LINEAR_ACCELERATION_METRIC_CLASS_SET;
            private static final SortedSet<MetricGroupType> ROTATION_VECTOR_METRIC_CLASS_SET;
            private static final SortedSet<MetricGroupType> GEO_ROTATION_VECTOR_METRIC_CLASS_SET;
            private static final SortedSet<MetricGroupType> EMPTY_SET;

            static {
                GENERAL_METRIC_CLASS_SET = new TreeSet<>(Set.of(MetricGroupType.TIME_METRIC_GROUP));
                ACCELEROMETER_METRIC_CLASS_SET = new TreeSet<>(
                        Set.of(MetricGroupType.AXIS_X_METRIC_GROUP,
                                MetricGroupType.AXIS_Y_METRIC_GROUP,
                                MetricGroupType.AXIS_Z_METRIC_GROUP));
                GYROSCOPE_METRIC_CLASS_SET = new TreeSet<>(
                        Set.of(MetricGroupType.AXIS_X_METRIC_GROUP,
                                MetricGroupType.AXIS_Y_METRIC_GROUP,
                                MetricGroupType.AXIS_Z_METRIC_GROUP));
                MAGNETOMETER_METRIC_CLASS_SET = new TreeSet<>(
                        Set.of(MetricGroupType.AXIS_STATIC_X_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_Y_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_Z_METRIC_GROUP));
                GRAVITY_METRIC_CLASS_SET = new TreeSet<>(
                        Set.of(MetricGroupType.AXIS_STATIC_X_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_Y_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_Z_METRIC_GROUP));
                LINEAR_ACCELERATION_METRIC_CLASS_SET = new TreeSet<>(
                        Set.of(MetricGroupType.AXIS_X_METRIC_GROUP,
                                MetricGroupType.AXIS_Y_METRIC_GROUP,
                                MetricGroupType.AXIS_Z_METRIC_GROUP));
                ROTATION_VECTOR_METRIC_CLASS_SET = new TreeSet<>(
                        Set.of(MetricGroupType.AXIS_STATIC_X_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_Y_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_Z_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_W_METRIC_GROUP));
                GEO_ROTATION_VECTOR_METRIC_CLASS_SET = new TreeSet<>(
                        Set.of(MetricGroupType.AXIS_STATIC_X_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_Y_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_Z_METRIC_GROUP,
                                MetricGroupType.AXIS_STATIC_W_METRIC_GROUP));
                EMPTY_SET = new TreeSet<>();
            }

            public static SortedSet<MetricGroupType> getMetricGroupForClass(
                    MetricClassType classType) {
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
                        return EMPTY_SET;
                }
            }

            public static SortedSet<MetricGroupType> getMetricGroupForGeneralClass() {
                return GENERAL_METRIC_CLASS_SET;
            }

            public static SortedSet<MetricGroupType> getMetricGroupForAccelerometerClass() {
                return ACCELEROMETER_METRIC_CLASS_SET;
            }

            public static SortedSet<MetricGroupType> getMetricGroupForGyroscopeClass() {
                return GYROSCOPE_METRIC_CLASS_SET;
            }


            public static SortedSet<MetricGroupType> getMetricGroupForMagnetometerClass() {
                return MAGNETOMETER_METRIC_CLASS_SET;
            }

            public static SortedSet<MetricGroupType> getMetricGroupForGravityClass() {
                return GRAVITY_METRIC_CLASS_SET;
            }

            public static SortedSet<MetricGroupType> getMetricLinearAccelerationClass() {
                return LINEAR_ACCELERATION_METRIC_CLASS_SET;
            }

            public static SortedSet<MetricGroupType> getMetricGroupForRotationVectorClass() {
                return ROTATION_VECTOR_METRIC_CLASS_SET;
            }

            public static SortedSet<MetricGroupType> getMetricGroupForGeoRotationVectorClass() {
                return GEO_ROTATION_VECTOR_METRIC_CLASS_SET;
            }
        }

        public static final class Metric {
            public static final SortedSet<MetricType> TIME_METRIC_GROUP_SET;
            public static final SortedSet<MetricType> AXIS_X_METRIC_GROUP_SET;
            public static final SortedSet<MetricType> AXIS_Y_METRIC_GROUP_SET;
            public static final SortedSet<MetricType> AXIS_Z_METRIC_GROUP_SET;
            public static final SortedSet<MetricType> AXIS_W_METRIC_GROUP_SET;
            public static final SortedSet<MetricType> AXIS_STATIC_X_METRIC_GROUP_SET;
            public static final SortedSet<MetricType> AXIS_STATIC_Y_METRIC_GROUP_SET;
            public static final SortedSet<MetricType> AXIS_STATIC_Z_METRIC_GROUP_SET;
            public static final SortedSet<MetricType> AXIS_STATIC_W_METRIC_GROUP_SET;
            public static final SortedSet<MetricType> EMPTY_SET;

            static {
                TIME_METRIC_GROUP_SET = new TreeSet<>(Set.of(MetricType.SPREAD_METRIC));
                AXIS_X_METRIC_GROUP_SET = new TreeSet<>(
                        Set.of(MetricType.MIN_METRIC, MetricType.MAX_METRIC,
                                MetricType.SPREAD_METRIC));
                AXIS_Y_METRIC_GROUP_SET = new TreeSet<>(
                        Set.of(MetricType.MIN_METRIC, MetricType.MAX_METRIC,
                                MetricType.SPREAD_METRIC));
                AXIS_Z_METRIC_GROUP_SET = new TreeSet<>(
                        Set.of(MetricType.MIN_METRIC, MetricType.MAX_METRIC,
                                MetricType.SPREAD_METRIC));
                AXIS_W_METRIC_GROUP_SET = new TreeSet<>(
                        Set.of(MetricType.MIN_METRIC, MetricType.MAX_METRIC,
                                MetricType.SPREAD_METRIC));
                AXIS_STATIC_X_METRIC_GROUP_SET = new TreeSet<>(Set.of(MetricType.ARITHMETIC_MEAN));
                AXIS_STATIC_Y_METRIC_GROUP_SET = new TreeSet<>(Set.of(MetricType.ARITHMETIC_MEAN));
                AXIS_STATIC_Z_METRIC_GROUP_SET = new TreeSet<>(Set.of(MetricType.ARITHMETIC_MEAN));
                AXIS_STATIC_W_METRIC_GROUP_SET = new TreeSet<>(Set.of(MetricType.ARITHMETIC_MEAN));
                EMPTY_SET = new TreeSet<>();
            }


            public static SortedSet<MetricType> getMetricForGroup(MetricGroupType metricGroupType) {
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
                        return EMPTY_SET;
                }
            }

            public static SortedSet<MetricType> getMetricForTimeGroup() {
                return TIME_METRIC_GROUP_SET;
            }

            public static SortedSet<MetricType> getMetricForAxisXGroup() {
                return AXIS_X_METRIC_GROUP_SET;
            }

            public static SortedSet<MetricType> getMetricForAxisYGroup() {
                return AXIS_Y_METRIC_GROUP_SET;
            }

            public static SortedSet<MetricType> getMetricForAxisZGroup() {
                return AXIS_Z_METRIC_GROUP_SET;
            }

            public static SortedSet<MetricType> getMetricForAxisWGroup() {
                return AXIS_W_METRIC_GROUP_SET;
            }

            public static SortedSet<MetricType> getMetricForStaticAxisXGroup() {
                return AXIS_STATIC_X_METRIC_GROUP_SET;
            }

            public static SortedSet<MetricType> getMetricForStaticAxisYGroup() {
                return AXIS_STATIC_Y_METRIC_GROUP_SET;
            }

            public static SortedSet<MetricType> getMetricForStaticAxisZGroup() {
                return AXIS_STATIC_Z_METRIC_GROUP_SET;
            }

            public static SortedSet<MetricType> getMetricForStaticAxisWGroup() {
                return AXIS_STATIC_W_METRIC_GROUP_SET;
            }
        }

        public static final class Network {
            public static int getNumberOfUnrecognizedEpisodes() {
                return 3;
            }

            public static double getAcceptableSpread() {
                return 0.1;
            }

            public static boolean shouldUpdateOnAccept() {
                return true;
            }
        }
    }
}
