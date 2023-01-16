package michael.linker.gestureid.ui.view.elementary.chart.line.sensor;

import java.util.List;

import michael.linker.gestureid.ui.view.elementary.chart.line.ILineChart;

public interface ISensorDataLineChart<T> extends ILineChart {
    /**
     * Provide new data for the chart.
     *
     * @param data Data model of the chart.
     */
    void addData(T data);

    /**
     * Provide new data list for the chart.
     *
     * @param dataList Data model list of the chart.
     */
    void addDataList(List<T> dataList);
}
