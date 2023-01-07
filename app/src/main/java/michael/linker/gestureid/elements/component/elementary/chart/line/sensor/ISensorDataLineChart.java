package michael.linker.gestureid.elements.component.elementary.chart.line.sensor;

import michael.linker.gestureid.elements.component.elementary.chart.line.ILineChart;

public interface ISensorDataLineChart<T> extends ILineChart {
    /**
     * Provide new data for the chart.
     *
     * @param data Data model of the chart.
     */
    void addData(T data);
}
