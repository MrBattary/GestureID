package michael.linker.gestureid.data.system.network;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.metric.Metric;
import michael.linker.gestureid.data.system.metric.exception.MetricNotFoundException;
import michael.linker.gestureid.data.system.metric.model.AddMetricModel;
import michael.linker.gestureid.data.system.metric.model.GetMetricModel;
import michael.linker.gestureid.data.system.metric.type.MetricClassType;
import michael.linker.gestureid.data.system.metric.type.MetricGroupType;
import michael.linker.gestureid.data.system.metric.type.MetricType;
import michael.linker.gestureid.data.system.network.type.SystemNetworkResult;

public class LocalSystemNetwork implements ISystemNetwork, IPersistentSystemNetwork {
    private static final String TAG = LocalSystemNetwork.class.getCanonicalName();

    private static final double SPREAD = SystemConfiguration.Build.Network.getAcceptableSpread();
    private final List<EpisodeMetrics> localNodesList;

    public LocalSystemNetwork() {
        localNodesList = new LinkedList<>();
        Log.w(TAG, "Local system network have no nodes to load from the persistent storage");
    }

    @Override
    public SystemNetworkResult proceed(EpisodeMetrics metrics) {
        for (EpisodeMetrics networkMetrics : localNodesList) {
            if (isAcceptable(networkMetrics.getMetric(), metrics.getMetric())) {
                if (SystemConfiguration.Build.Network.shouldUpdateOnAccept()) {
                    updateMetrics(networkMetrics.getMetric(), metrics.getMetric());
                }
                return SystemNetworkResult.RECOGNIZED;
            }
        }
        return SystemNetworkResult.NOT_RECOGNIZED;
    }

    protected boolean isAcceptable(Metric<Double> existMetrics, Metric<Double> comparedMetrics) {
        for (MetricClassType metricClass : existMetrics.getClasses()) {
            for (MetricGroupType metricGroup : existMetrics.getGroupsForClass(metricClass)) {
                for (MetricType metric :
                        existMetrics.getMetricsForGroupAndClass(metricClass, metricGroup)) {
                    GetMetricModel metricGetModel =
                            new GetMetricModel(metricClass, metricGroup, metric);
                    try {
                        Double comparedMetricValue = comparedMetrics.getMetric(metricGetModel);
                        Double existMetricValue = existMetrics.getMetric(metricGetModel);
                        if (comparedMetricValue.doubleValue() == existMetricValue.doubleValue()) {
                            continue;
                        }
                        double providedMetricValueUpperBorder =
                                generateBorder(existMetricValue, true);
                        double providedMetricValueLowerBorder =
                                generateBorder(existMetricValue, false);
                        if (providedMetricValueLowerBorder > comparedMetricValue
                                || comparedMetricValue > providedMetricValueUpperBorder) {
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

    private double generateBorder(double metricValue, boolean isUpperBorder) {
        if (metricValue >= -1.0 && metricValue <= 1.0) {
            if (isUpperBorder) {
                return metricValue + SPREAD;
            } else {
                return metricValue - SPREAD;
            }
        }
        if (metricValue > 0) {
            if (isUpperBorder) {
                return metricValue * (1 + SPREAD);
            } else {
                return metricValue * (1 - SPREAD);
            }
        } else {
            if (isUpperBorder) {
                return metricValue * (1 - SPREAD);
            } else {
                return metricValue * (1 + SPREAD);
            }
        }
    }

    private void updateMetrics(Metric<Double> existMetrics, Metric<Double> newMetric) {
        for (MetricClassType metricClass : existMetrics.getClasses()) {
            for (MetricGroupType metricGroup : existMetrics.getGroupsForClass(metricClass)) {
                for (MetricType metric :
                        existMetrics.getMetricsForGroupAndClass(metricClass, metricGroup)) {
                    GetMetricModel metricGetModel =
                            new GetMetricModel(metricClass, metricGroup, metric);

                    Double newMetricValue = newMetric.getMetric(metricGetModel);
                    Double existMetricValue = existMetrics.getMetric(metricGetModel);
                    Double newAverageMetricValue = (newMetricValue + existMetricValue) / 2;

                    existMetrics.putMetric(new AddMetricModel<>(
                            metricClass, metricGroup, metric, newAverageMetricValue));
                }
            }
        }
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

    @Override
    public void persist() {
        Log.w(TAG, "Local system network cant persist nodes to the storage!");
    }
}
