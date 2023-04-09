package michael.linker.gestureid.analyzer.calculator.addons.intersection;

import michael.linker.gestureid.analyzer.metric.Metric;
import michael.linker.gestureid.analyzer.metric.model.GetMetricModel;
import michael.linker.gestureid.analyzer.metric.type.MetricClassType;
import michael.linker.gestureid.analyzer.metric.type.MetricGroupType;
import michael.linker.gestureid.analyzer.metric.type.MetricType;
import michael.linker.gestureid.analyzer.user.model.UserModel;
import michael.linker.gestureid.analyzer.user.model.node.UserModelNode;

public class DispersionIntersector {
    private final Double dispersion;

    public DispersionIntersector(Double dispersion) {
        this.dispersion = dispersion;
    }

    /**
     * Intersects nodes of two user models until the first match of compared node with any comparable node.
     *
     * @param compared   Compared user model.
     * @param comparable Comparable user model.
     * @return The number of node matches in the compared model with the comparable model.
     * This value <= number of nodes in the compared user model.
     */
    public int intersectModels(UserModel compared, UserModel comparable) {
        int intersectedNodes = 0;
        for (UserModelNode comparedNode : compared.getNodes()) {
            for (UserModelNode comparableNode : comparable.getNodes()) {
                if (isNodesFullyIntersect(comparedNode, comparableNode)) {
                    intersectedNodes += 1;
                    break;
                }
            }
        }
        return intersectedNodes;
    }

    /**
     * Fully intersects nodes of two user models.
     *
     * @param compared   Compared user model.
     * @param comparable Comparable user model.
     * @return The number of nodes intersected.
     * This value <= number of nodes in the compared user model * number of nodes in the comparable user model.
     */
    public int intersectAllModels(UserModel compared, UserModel comparable) {
        int intersectedNodes = 0;
        for (UserModelNode comparedNode : compared.getNodes()) {
            for (UserModelNode comparableNode : comparable.getNodes()) {
                if (isNodesFullyIntersect(comparedNode, comparableNode)) {
                    intersectedNodes += 1;
                }
            }
        }
        return intersectedNodes;
    }

    private boolean isNodesFullyIntersect(UserModelNode comparedNode, UserModelNode comparableNode) {
        final Metric<Double> comparedMetric = comparedNode.metric();
        final Metric<Double> comparableMetric = comparableNode.metric();

        for (MetricClassType metricClass : comparedMetric.getClasses()) {
            for (MetricGroupType metricGroup : comparedMetric.getGroupsForClass(metricClass)) {
                for (MetricType metric :
                        comparedMetric.getMetricsForGroupAndClass(metricClass, metricGroup)) {
                    GetMetricModel metricGetModel =
                            new GetMetricModel(metricClass, metricGroup, metric);

                    Double comparedMetricValue = comparedMetric.getMetric(metricGetModel);
                    Double comparableMetricValue = comparableMetric.getMetric(metricGetModel);

                    if (comparableMetricValue.doubleValue() == comparedMetricValue.doubleValue()) {
                        continue;
                    }
                    double comparedMetricValueUpperBorder =
                            generateBorder(comparedMetricValue, true);
                    double comparedMetricValueLowerBorder =
                            generateBorder(comparedMetricValue, false);
                    if (comparedMetricValueLowerBorder > comparableMetricValue
                            || comparableMetricValue > comparedMetricValueUpperBorder) {
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
                return metricValue + dispersion;
            } else {
                return metricValue - dispersion;
            }
        }
        if (metricValue > 0) {
            if (isUpperBorder) {
                return metricValue * (1 + dispersion);
            } else {
                return metricValue * (1 - dispersion);
            }
        } else {
            if (isUpperBorder) {
                return metricValue * (1 - dispersion);
            } else {
                return metricValue * (1 + dispersion);
            }
        }
    }
}
