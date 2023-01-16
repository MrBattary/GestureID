package michael.linker.gestureid.ui.view.elementary.chart.line.sensor.sizable;

import android.view.View;

import com.github.mikephil.charting.components.YAxis;

import michael.linker.gestureid.ui.view.elementary.chart.line.sensor.ISensorDataLineChart;
import michael.linker.gestureid.ui.view.elementary.chart.line.sensor.SensorDataLineChart;
import michael.linker.gestureid.data.sensor.model.ASensorModel;

public class SensorDataSizableLineChart extends SensorDataLineChart
        implements ISensorDataLineChart<ASensorModel<Double>> {
    private final SensorDataSizableLineChartProperties chartProperties;

    public SensorDataSizableLineChart(View view,
            SensorDataSizableLineChartProperties chartProperties) {
        super(view, chartProperties);
        this.chartProperties = chartProperties;
        setupYAxis();
    }

    private void setupYAxis() {
        YAxis yAxis = super.chart.getAxisLeft();
        yAxis.setAxisMaximum(chartProperties.getYAxisMinMax());
        yAxis.setAxisMinimum(-chartProperties.getYAxisMinMax());
    }
}
