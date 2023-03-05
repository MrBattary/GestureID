package michael.linker.gestureid.data.system.calculator.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.core.sensor.sensor.type.SensorAxisType;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.data.sensor.model.ASensorModel;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;

public class RearrangedAccumulatedEpisode {
    private final Map<MetricClassType, Map<MetricGroupType, List<Double>>> data;

    public RearrangedAccumulatedEpisode(AccumulatedEpisode episode) {
        data = new HashMap<>();

        addTimeData(episode);
        addAxisData(episode);
    }

    private void addTimeData(AccumulatedEpisode episode) {
        List<Double> buffer = new LinkedList<>();
        List<SynchronizedEvent> eventList = episode.getData();
        for (SynchronizedEvent synchronizedEvent : eventList) {
            buffer.add((Double) synchronizedEvent.getTimestamp());
        }
        data.put(MetricClassType.GENERAL_METRIC_CLASS,
                Map.of(MetricGroupType.TIME_METRIC_GROUP, buffer));
    }

    private void addAxisData(AccumulatedEpisode episode) {
        List<SynchronizedEvent> eventList = episode.getData();
        Set<MetricClassType> requiredMetricClassSet = getRequiredMetricClasses();

        for (SynchronizedEvent synchronizedEvent : eventList) {
            for (ASensorModel<Double> sensorModel : synchronizedEvent.getData()) {
                MetricClassType metricClassType =
                        MetricClassType.fromInt(sensorModel.getSensorType().toInt());

                if (requiredMetricClassSet.contains(metricClassType)) {
                    if (metricClassType != MetricClassType.UNDEFINED_METRIC_CLASS) {

                        data.computeIfAbsent(metricClassType, k -> {
                            Map<MetricGroupType, List<Double>> groupMap = new HashMap<>();
                            for (SensorAxisType sensorAxisType : sensorModel.getAxisList()) {
                                groupMap.put(MetricGroupType.fromSensorAxisType(sensorAxisType),
                                        new LinkedList<>());
                            }
                            return groupMap;
                        });
                    }

                    Map<SensorAxisType, Double> axisValueMap = sensorModel.getAxisValueMap();
                    for (SensorAxisType sensorAxisType : axisValueMap.keySet()) {
                        data
                                .get(metricClassType)
                                .get(MetricGroupType.fromSensorAxisType(sensorAxisType))
                                .add(axisValueMap.get(sensorAxisType));
                    }
                }
            }
        }
    }

    public List<Double> getListData(
            MetricClassType metricClassType,
            MetricGroupType metricGroupType) {
        return data
                .get(metricClassType).get(metricGroupType);
    }

    private Set<MetricClassType> getRequiredMetricClasses() {
        Set<MetricClassType> requiredMetricClassSet = new HashSet<>();
        for (MetricClassType metricClassType : MetricClassType.values()) {
            if (SystemConfiguration.Build.Class.isMetricClassRequired(metricClassType)) {
                requiredMetricClassSet.add(metricClassType);
            }
        }
        return requiredMetricClassSet;
    }
}
