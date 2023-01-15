package michael.linker.gestureid.data.system.metric;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.data.system.metric.exception.MetricNotFoundException;
import michael.linker.gestureid.data.system.metric.exception.MetricNotRequiredException;
import michael.linker.gestureid.data.system.metric.model.AddMetricModel;
import michael.linker.gestureid.data.system.metric.model.GetMetricModel;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;
import michael.linker.gestureid.data.system.metric.type.MetricType;

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

    private void initMetricMapClasses() {
        metricMap.put(MetricClassType.GENERAL_METRIC_CLASS, new HashMap<>());
        if (SystemConfiguration.Build.isAccelerometerMetricClassRequired()) {
            metricMap.put(MetricClassType.ACCELEROMETER_METRIC_CLASS, new HashMap<>());
        }
        if (SystemConfiguration.Build.isGyroscopeMetricClassRequired()) {
            metricMap.put(MetricClassType.GYROSCOPE_METRIC_CLASS, new HashMap<>());
        }
        if (SystemConfiguration.Build.isMagnetometerMetricClassRequired()) {
            metricMap.put(MetricClassType.MAGNETOMETER_METRIC_CLASS, new HashMap<>());
        }
        if (SystemConfiguration.Build.isGravityMetricClassRequired()) {
            metricMap.put(MetricClassType.GRAVITY_METRIC_CLASS, new HashMap<>());
        }
        if (SystemConfiguration.Build.isLinearAccelerationMetricClassRequired()) {
            metricMap.put(MetricClassType.LINEAR_ACCELERATION_METRIC_CLASS, new HashMap<>());
        }
        if (SystemConfiguration.Build.isRotationVectorMetricClassRequired()) {
            metricMap.put(MetricClassType.ROTATION_VECTOR_METRIC_CLASS, new HashMap<>());
        }
        if (SystemConfiguration.Build.isGeoRotationVectorMetricClassRequired()) {
            metricMap.put(MetricClassType.GEO_ROTATION_VECTOR_METRIC_CLASS, new HashMap<>());
        }
    }
}
