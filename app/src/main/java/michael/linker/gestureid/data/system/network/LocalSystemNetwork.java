package michael.linker.gestureid.data.system.network;

import java.util.LinkedList;
import java.util.List;

import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.metric.Metric;
import michael.linker.gestureid.data.system.metric.exception.MetricNotFoundException;
import michael.linker.gestureid.data.system.metric.model.GetMetricModel;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;
import michael.linker.gestureid.data.system.metric.type.MetricType;
import michael.linker.gestureid.data.system.network.type.SystemNetworkResult;

public class LocalSystemNetwork implements ISystemNetwork {
    private static final double SPREAD = SystemConfiguration.Build.Network.getAcceptableSpread();
    private final List<EpisodeMetrics> localNodesList;

    public LocalSystemNetwork() {
        localNodesList = new LinkedList<>();
    }

    @Override
    public SystemNetworkResult proceed(EpisodeMetrics metrics) {
        for (EpisodeMetrics networkMetrics : localNodesList) {
            if (isAcceptable(networkMetrics.getMetric(), metrics.getMetric())) {
                return SystemNetworkResult.RECOGNIZED;
            }
        }
        return SystemNetworkResult.NOT_RECOGNIZED;
    }

    protected boolean isAcceptable(Metric<Double> providedMetrics, Metric<Double> comparedMetrics) {
        for (MetricClassType metricClass : providedMetrics.getClasses()) {
            for (MetricGroupType metricGroup : providedMetrics.getGroupsForClass(metricClass)) {
                for (MetricType metric : providedMetrics.getMetricsForGroupAndClass(metricClass,
                        metricGroup)) {
                    GetMetricModel metricGetModel = new GetMetricModel(metricClass, metricGroup,
                            metric);
                    try {
                        Double comparedMetricValue = comparedMetrics.getMetric(metricGetModel);
                        Double providedMetricValue = providedMetrics.getMetric(metricGetModel);
                        double providedMetricValueUpperBorder = providedMetricValue * (1 + SPREAD);
                        double providedMetricValueLowerBorder = providedMetricValue * (1 - SPREAD);
                        if (providedMetricValueLowerBorder >= comparedMetricValue
                                || comparedMetricValue >= providedMetricValueUpperBorder) {
                            return false;
                        }
                    } catch (MetricNotFoundException e) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void create(EpisodeMetrics metrics) {
        localNodesList.add(metrics);
    }

    @Override
    public List<EpisodeMetrics> getNodes() {
        return localNodesList;
    }

    @Override
    public void purgeNodes() {
        localNodesList.clear();
    }
}
