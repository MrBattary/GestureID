package michael.linker.gestureid.ui.view.elementary.chart.line;

import michael.linker.gestureid.ui.view.elementary.chart.IChart;

public interface ILineChart extends IChart {
    /**
     * Clears the chart from all data (sets it to null) and refreshes it (by calling invalidate()).
     */
    void clear();
}
