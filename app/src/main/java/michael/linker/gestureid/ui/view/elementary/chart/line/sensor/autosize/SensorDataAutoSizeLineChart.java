package michael.linker.gestureid.ui.view.elementary.chart.line.sensor.autosize;

import android.view.View;

import com.github.mikephil.charting.components.YAxis;

import michael.linker.gestureid.ui.view.elementary.chart.line.sensor.ISensorDataLineChart;
import michael.linker.gestureid.ui.view.elementary.chart.line.sensor.SensorDataLineChart;
import michael.linker.gestureid.data.sensor.model.ASensorModel;

public class SensorDataAutoSizeLineChart extends SensorDataLineChart
        implements ISensorDataLineChart<ASensorModel<Double>> {
    private final SensorDataAutoSizeLineChartProperties chartProperties;

    public SensorDataAutoSizeLineChart(View view,
            SensorDataAutoSizeLineChartProperties chartProperties) {
        super(view, chartProperties);
        this.chartProperties = chartProperties;
        setupYAxis();
    }

    private void setupYAxis() {
        YAxis yAxis = super.chart.getAxisLeft();
        yAxis.setSpaceTop(chartProperties.getYAxisSpacePercent());
        yAxis.setSpaceBottom(chartProperties.getYAxisSpacePercent());
    }
}
