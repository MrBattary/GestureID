package michael.linker.gestureid.ui.view.elementary.chart.line.sensor.autosize;

import michael.linker.gestureid.ui.view.elementary.chart.line.sensor.SensorDataLineChartProperties;

public class SensorDataAutoSizeLineChartProperties extends SensorDataLineChartProperties {
    private final float yAxisSpacePercent;

    public SensorDataAutoSizeLineChartProperties(int chartMaxSize, float verticalPercent) {
        super(chartMaxSize);
        yAxisSpacePercent = verticalPercent;
    }

    public float getYAxisSpacePercent() {
        return yAxisSpacePercent;
    }
}
