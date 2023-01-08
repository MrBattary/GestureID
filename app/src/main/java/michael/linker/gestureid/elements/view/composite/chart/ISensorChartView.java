package michael.linker.gestureid.elements.view.composite.chart;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import java.util.List;

import michael.linker.gestureid.elements.view.IView;
import michael.linker.gestureid.sensor.model.ASensorModel;

public interface ISensorChartView extends IView {
    /**
     * Subscribe live data updates to the chart consumer.
     *
     * @param lifecycleOwner use getViewLifecycleOwner().
     * @param liveData       live data from the ViewModel.
     */
    void subscribe(LifecycleOwner lifecycleOwner, LiveData<ASensorModel<Float>> liveData);

    /**
     * Subscribe live data updates to the chart consumer.
     *
     * @param lifecycleOwner use getViewLifecycleOwner().
     * @param liveData       live data from the ViewModel.
     */
    void subscribeList(LifecycleOwner lifecycleOwner, LiveData<List<ASensorModel<Float>>> liveData);

    /**
     * Clears the chart from all data (sets it to null) and refreshes it.
     */
    void clear();
}
