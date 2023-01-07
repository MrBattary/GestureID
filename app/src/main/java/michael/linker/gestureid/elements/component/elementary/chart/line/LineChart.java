package michael.linker.gestureid.elements.component.elementary.chart.line;

import android.view.View;

import michael.linker.gestureid.R;
import michael.linker.gestureid.elements.component.elementary.chart.IChart;

public class LineChart implements IChart {
    protected final com.github.mikephil.charting.charts.LineChart chart;

    public LineChart(View view) {
        chart = view.findViewById(R.id.sensor_chart_line);
    }
}
