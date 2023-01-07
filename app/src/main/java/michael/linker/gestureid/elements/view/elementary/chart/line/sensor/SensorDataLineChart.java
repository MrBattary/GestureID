package michael.linker.gestureid.elements.view.elementary.chart.line.sensor;

import android.view.View;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import michael.linker.gestureid.R;
import michael.linker.gestureid.core.sensor.sensor.type.SensorAxisType;
import michael.linker.gestureid.data.res.ColorsProvider;
import michael.linker.gestureid.data.res.DimensionsProvider;
import michael.linker.gestureid.elements.view.elementary.chart.line.LineChart;
import michael.linker.gestureid.sensor.model.ASensorModel;

public class SensorDataLineChart extends LineChart implements
        ISensorDataLineChart<ASensorModel<Float>> {
    private final SensorDataLineChartProperties chartProperties;
    private final Map<SensorAxisType, LinkedList<Entry>> axisValuesMap;

    protected SensorDataLineChart(View view, SensorDataLineChartProperties chartProperties) {
        super(view);
        this.chartProperties = chartProperties;
        axisValuesMap = new HashMap<>();

        setupGeneral();
        setupXAxis();
        setupYAxis();
        super.chart.invalidate();
    }

    private void setupGeneral() {
        super.chart.getDescription().setEnabled(false);
        super.chart.setTouchEnabled(false);
        super.chart.setDragEnabled(false);
        super.chart.setScaleEnabled(false);
    }

    private void setupXAxis() {
        super.chart.getXAxis().setDrawLabels(false);
        super.chart.getXAxis().setDrawGridLines(false);
    }

    private void setupYAxis() {
        super.chart.getAxisRight().setEnabled(false);
    }


    @Override
    public void addData(ASensorModel<Float> data) {
        LineData lineData = new LineData();
        for (SensorAxisType axis : data.getAxisList()) {
            if (!axisValuesMap.containsKey(axis)) {
                axisValuesMap.put(axis, new LinkedList<>());
            }
            LinkedList<Entry> axisValueList = axisValuesMap.get(axis);

            if (axisValueList.size() == chartProperties.getChartMaxSize()) {
                axisValueList.removeFirst();
            }

            axisValueList.addLast(
                    new Entry(
                            data.getTimestamp().floatValue(),
                            data.getAxisValueMap().get(axis)
                    )
            );
            lineData.addDataSet(buildLineDataSet(axisValueList, axis));
        }
        super.chart.setData(lineData);

        super.chart.invalidate();
    }

    private LineDataSet buildLineDataSet(List<Entry> values, SensorAxisType axis) {
        LineDataSet axisDataSet = new LineDataSet(values, axis.toString());
        axisDataSet.setDrawCircles(false);
        axisDataSet.setDrawValues(false);
        axisDataSet.setColor(ColorsProvider.Axis.getColorForAxis(axis));
        axisDataSet.setLineWidth(DimensionsProvider.getDpExtracted(R.dimen.chart_line_width));
        return axisDataSet;
    }
}
