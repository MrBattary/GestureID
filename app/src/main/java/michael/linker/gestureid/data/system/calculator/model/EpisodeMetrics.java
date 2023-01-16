package michael.linker.gestureid.data.system.calculator.model;

import michael.linker.gestureid.data.system.metric.Metric;

public class EpisodeMetrics {
    private final Metric<Double> metric;

    public EpisodeMetrics(Metric<Double> metric) {
        this.metric = metric;
    }

    public Metric<Double> getMetric() {
        return metric;
    }
}
