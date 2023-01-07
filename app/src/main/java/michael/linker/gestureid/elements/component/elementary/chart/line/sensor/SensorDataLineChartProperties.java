package michael.linker.gestureid.elements.component.elementary.chart.line.sensor;

import michael.linker.gestureid.elements.component.elementary.chart.line.LineChartProperties;

public class SensorDataLineChartProperties extends LineChartProperties {
    private final int chartMaxSize;

    public SensorDataLineChartProperties(int chartMaxSize) {
        this.chartMaxSize = chartMaxSize;
    }

    public int getChartMaxSize() {
        return chartMaxSize;
    }

}
