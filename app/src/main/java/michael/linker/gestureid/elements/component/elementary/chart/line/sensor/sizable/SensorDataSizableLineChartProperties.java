package michael.linker.gestureid.elements.component.elementary.chart.line.sensor.sizable;

import michael.linker.gestureid.elements.component.elementary.chart.line.sensor.SensorDataLineChartProperties;

public class SensorDataSizableLineChartProperties extends SensorDataLineChartProperties {
    private final float yAxisMinMax;

    public SensorDataSizableLineChartProperties(int chartMaxSize, float yAxisMinMax) {
        super(chartMaxSize);
        this.yAxisMinMax = yAxisMinMax < 0 ? -yAxisMinMax : yAxisMinMax;
    }

    public float getYAxisMinMax() {
        return yAxisMinMax;
    }
}
