package michael.linker.gestureid.elements.view.composite.chart;

import android.content.Context;
import android.hardware.Sensor;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.sensor.SensorProviderConfiguration;
import michael.linker.gestureid.data.res.NumbersProvider;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.elements.view.elementary.chart.line.sensor.ISensorDataLineChart;
import michael.linker.gestureid.elements.view.elementary.chart.line.sensor.autosize.SensorDataAutoSizeLineChart;
import michael.linker.gestureid.elements.view.elementary.chart.line.sensor.autosize.SensorDataAutoSizeLineChartProperties;
import michael.linker.gestureid.elements.view.elementary.dialog.IDialog;
import michael.linker.gestureid.elements.view.elementary.dialog.single.SingleChoiceDialogModel;
import michael.linker.gestureid.elements.view.elementary.dialog.single.SingleChoiceInfoDialog;
import michael.linker.gestureid.sensor.model.ASensorModel;
import michael.linker.gestureid.sensor.provider.SensorProviderNotFoundException;

public class SensorChartView implements ISensorChartView {
    private static final SensorDataAutoSizeLineChartProperties CHART_PROPERTIES;
    private final SensorChartViewData viewData;
    private final View rootView;
    private final ViewGroup contentViewGroup, noContentViewGroup;
    private final MaterialTextView heading, headingNoContent;
    private final MaterialButton infoButton;
    private final ISensorDataLineChart<ASensorModel<Float>> chart;
    private IDialog infoDialog;

    static {
        CHART_PROPERTIES = new SensorDataAutoSizeLineChartProperties(
                NumbersProvider.getInteger(R.dimen.chart_max_size),
                NumbersProvider.getFloat(R.dimen.chart_y_axis_percent)
        );
    }

    public SensorChartView(Context ctx, View view, SensorChartViewData data) {
        viewData = data;
        rootView = view;

        contentViewGroup = view.findViewById(R.id.sensor_chart_content);
        noContentViewGroup = view.findViewById(R.id.sensor_chart_content_not_found);

        heading = view.findViewById(R.id.sensor_chart_header_name);
        headingNoContent = view.findViewById(R.id.sensor_chart_heading_not_found);
        infoButton = view.findViewById(R.id.sensor_chart_header_info_button);

        chart = new SensorDataAutoSizeLineChart(
                view.findViewById(R.id.sensor_chart_line_chart),
                CHART_PROPERTIES
        );

        initFields();
        initDialogs(ctx);
        initButtons();
        disableChart();
    }

    private void initFields() {
        heading.setText(viewData.getHeadingText());
        headingNoContent.setText(viewData.getHeadingNoContentText());
    }

    @Override
    public void subscribe(LifecycleOwner lifecycleOwner, LiveData<ASensorModel<Float>> liveData) {
        enableChart();
        liveData.observe(lifecycleOwner, chart::addData);
    }

    @Override
    public void subscribeList(LifecycleOwner lifecycleOwner,
            LiveData<List<ASensorModel<Float>>> liveData) {
        enableChart();
        liveData.observe(lifecycleOwner, chart::addDataList);
    }

    @Override
    public void clear() {
        chart.clear();
    }


    @Override
    public View getViewInstance() {
        return rootView;
    }

    @Override
    public void setVisibility(int visibility) {
        rootView.setVisibility(visibility);
    }

    private void disableChart() {
        contentViewGroup.setVisibility(View.GONE);
        noContentViewGroup.setVisibility(View.VISIBLE);
    }

    private void enableChart() {
        contentViewGroup.setVisibility(View.VISIBLE);
        noContentViewGroup.setVisibility(View.GONE);
    }

    private void initDialogs(Context ctx) {


        infoDialog = new SingleChoiceInfoDialog(ctx,
                new SingleChoiceDialogModel(
                        StringsProvider.getString(R.string.sensor_info),
                        buildSensorData(),
                        StringsProvider.getString(R.string.button_ok)),
                (dialogInterface, i) -> dialogInterface.dismiss()
        );
    }

    private String buildSensorData() {
        String sensorData;
        try {
            Sensor sensor = SensorProviderConfiguration.getSensorProvider()
                    .getSensor(viewData.getSensorType()).getHardwareSensor();
            sensorData =
                    StringsProvider.getString(R.string.sensor_info_name) +
                            sensor.getName() + "\n" +
                            StringsProvider.getString(R.string.sensor_info_power)
                            + sensor.getPower() + "\n" +
                            StringsProvider.getString(R.string.sensor_info_delay)
                            + sensor.getMinDelay() + "\n" +
                            StringsProvider.getString(R.string.sensor_info_range)
                            + sensor.getMaximumRange() + "\n" +
                            StringsProvider.getString(R.string.sensor_info_resolution)
                            + sensor.getResolution();
        } catch (SensorProviderNotFoundException e) {
            sensorData = StringsProvider.getString(R.string.sensor_accelerometer_not_available);
        }
        return sensorData;
    }

    private void initButtons() {
        infoButton.setOnClickListener(l -> infoDialog.show());
    }
}
