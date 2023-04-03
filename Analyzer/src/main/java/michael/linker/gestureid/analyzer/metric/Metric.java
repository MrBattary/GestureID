package michael.linker.gestureid.analyzer.metric;

import michael.linker.gestureid.analyzer.config.MetricConfiguration;
import michael.linker.gestureid.analyzer.metric.exception.MetricNotFoundException;
import michael.linker.gestureid.analyzer.metric.exception.MetricNotRequiredException;
import michael.linker.gestureid.analyzer.metric.model.AddMetricModel;
import michael.linker.gestureid.analyzer.metric.model.GetMetricModel;
import michael.linker.gestureid.analyzer.metric.type.MetricClassType;
import michael.linker.gestureid.analyzer.metric.type.MetricGroupType;
import michael.linker.gestureid.analyzer.metric.type.MetricType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Metric<T> {
    private final Map<MetricClassType, Map<MetricGroupType, Map<MetricType, T>>> metricMap;

    public Metric() {
        metricMap = new HashMap<>();
        initMetricMapClasses();
    }

    public void putMetric(AddMetricModel<T> addModel) throws MetricNotRequiredException {
        Map<MetricGroupType, Map<MetricType, T>> metricClassMap =
                metricMap.get(addModel.getMetricClass());
        if (metricClassMap == null) {
            throw new MetricNotRequiredException(addModel.getMetricClass());
        }

        metricClassMap.computeIfAbsent(addModel.getMetricGroup(), k -> new HashMap<>());
        Map<MetricType, T> metricGroupMap = metricClassMap.get(addModel.getMetricGroup());
        metricGroupMap.put(addModel.getMetricType(), addModel.getMetricValue());
    }

    public T getMetric(GetMetricModel getModel) throws MetricNotFoundException {
        Map<MetricGroupType, Map<MetricType, T>> metricClassMap =
                metricMap.get(getModel.getMetricClass());
        if (metricClassMap == null) {
            throw new MetricNotFoundException(getModel.getMetricClass());
        }

        Map<MetricType, T> metricGroupMap = metricClassMap.get(getModel.getMetricGroup());
        if (metricGroupMap == null) {
            throw new MetricNotFoundException(getModel.getMetricGroup());
        }

        T metric = metricGroupMap.get(getModel.getMetricType());
        if (metric == null) {
            throw new MetricNotFoundException(getModel.getMetricType());
        }
        return metric;
    }

    public Set<MetricClassType> getClasses() {
        return metricMap.keySet();
    }

    public Set<MetricGroupType> getGroupsForClass(MetricClassType metricClass) {
        return metricMap
                .getOrDefault(metricClass, new HashMap<>())
                .keySet();
    }

    public Set<MetricType> getMetricsForGroupAndClass(
            MetricClassType metricClass,
            MetricGroupType metricGroup) {
        return metricMap
                .getOrDefault(metricClass, new HashMap<>())
                .getOrDefault(metricGroup, new HashMap<>())
                .keySet();
    }

    private void initMetricMapClasses() {
        metricMap.put(MetricClassType.GENERAL_METRIC_CLASS, new HashMap<>());
        if (MetricConfiguration.isAccelerometerMetricClassRequired()) {
            metricMap.put(MetricClassType.ACCELEROMETER_METRIC_CLASS, new HashMap<>());
        }
        if (MetricConfiguration.isGyroscopeMetricClassRequired()) {
            metricMap.put(MetricClassType.GYROSCOPE_METRIC_CLASS, new HashMap<>());
        }
        if (MetricConfiguration.isMagnetometerMetricClassRequired()) {
            metricMap.put(MetricClassType.MAGNETOMETER_METRIC_CLASS, new HashMap<>());
        }
        if (MetricConfiguration.isGravityMetricClassRequired()) {
            metricMap.put(MetricClassType.GRAVITY_METRIC_CLASS, new HashMap<>());
        }
        if (MetricConfiguration.isLinearAccelerationMetricClassRequired()) {
            metricMap.put(MetricClassType.LINEAR_ACCELERATION_METRIC_CLASS, new HashMap<>());
        }
        if (MetricConfiguration.isRotationVectorMetricClassRequired()) {
            metricMap.put(MetricClassType.ROTATION_VECTOR_METRIC_CLASS, new HashMap<>());
        }
        if (MetricConfiguration.isGeoRotationVectorMetricClassRequired()) {
            metricMap.put(MetricClassType.GEO_ROTATION_VECTOR_METRIC_CLASS, new HashMap<>());
        }
    }
}
