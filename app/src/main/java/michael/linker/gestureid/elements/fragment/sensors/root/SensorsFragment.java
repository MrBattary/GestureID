package michael.linker.gestureid.elements.fragment.sensors.root;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textview.MaterialTextView;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.config.sensor.SensorManagerConfiguration;
import michael.linker.gestureid.config.sensor.SensorsConfiguration;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.elements.view.composite.chart.ISensorChartView;
import michael.linker.gestureid.elements.view.composite.chart.SensorChartView;
import michael.linker.gestureid.elements.view.composite.chart.SensorChartViewData;

public class SensorsFragment extends Fragment {
    MaterialTextView timeView;
    ISensorChartView accelerometerChartView;

    private SensorsViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        NavController navController = NavHostFragment.findNavController(this);
        ViewModelStoreOwner viewModelStoreOwner = navController.getViewModelStoreOwner(
                R.id.root_navigation_sensors);
        viewModel = new ViewModelProvider(viewModelStoreOwner).get(SensorsViewModel.class);

        return inflater.inflate(R.layout.fragment_sensors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initSubscriptions();
    }

    private void initViews(View view) {
        timeView = view.findViewById(R.id.sensors_current_time);
        accelerometerChartView = new SensorChartView(
                requireContext(),
                view.findViewById(R.id.sensors_accelerometer_chart),
                new SensorChartViewData(
                        BaseSensorType.ACCELEROMETER,
                        StringsProvider.getString(R.string.sensor_accelerometer),
                        StringsProvider.getString(R.string.sensor_accelerometer_not_available)
                ));
        // TODO: Finish for the another sensors
    }

    private void initSubscriptions() {
        viewModel.getTimestamp().observe(getViewLifecycleOwner(),
                timestamp -> timeView.setText(timestamp));
        if (!SensorsConfiguration.Build.isAccelerometerDeactivated()) {
            accelerometerChartView.subscribe(getViewLifecycleOwner(),
                    viewModel.getAccelerometerEvent());
        }
        // TODO: Finish for the another sensors
    }

    @Override
    public void onResume() {
        EventAccumulatorConfiguration.getActiveAccumulator().subscribe(viewModel);
        SensorManagerConfiguration.getManager().unsuppressRegistering();
        super.onResume();
    }

    @Override
    public void onPause() {
        SensorManagerConfiguration.getManager().suppressRegistering();
        EventAccumulatorConfiguration.getActiveAccumulator().unsubscribe(viewModel);
        super.onPause();
    }
}