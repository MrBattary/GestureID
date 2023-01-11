package michael.linker.gestureid.ui.view.elementary.chart.line;

import android.view.View;

import michael.linker.gestureid.R;

public class LineChart implements ILineChart {
    protected final com.github.mikephil.charting.charts.LineChart chart;

    public LineChart(View view) {
        chart = view.findViewById(R.id.sensor_chart_line_chart);
    }

    @Override
    public void clear() {
        chart.clear();
    }
}
