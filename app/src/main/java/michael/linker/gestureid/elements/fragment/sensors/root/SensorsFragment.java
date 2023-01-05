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
import michael.linker.gestureid.elements.component.sensor.StubSensorComponent;

public class SensorsFragment extends Fragment {
    MaterialTextView timeView;
    StubSensorComponent accelerometerComponent, gyroscopeComponent, magnetometerComponent;

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
        timeView = view.findViewById(R.id.sensors_time);
        accelerometerComponent = new StubSensorComponent(
                view.findViewById(R.id.sensors_accelerometer));
        accelerometerComponent.setName(BaseSensorType.ACCELEROMETER.toString());
        accelerometerComponent.setStatus(
                SensorsConfiguration.Build.isAccelerometerDeactivated() ? "inactive" : "active");
        gyroscopeComponent = new StubSensorComponent(view.findViewById(R.id.sensors_gyroscope));
        gyroscopeComponent.setName(BaseSensorType.GYROSCOPE.toString());
        gyroscopeComponent.setStatus(
                SensorsConfiguration.Build.isGyroscopeDeactivated() ? "inactive" : "active");
        magnetometerComponent = new StubSensorComponent(
                view.findViewById(R.id.sensors_magnetometer));
        magnetometerComponent.setName(BaseSensorType.MAGNETOMETER.toString());
        magnetometerComponent.setStatus(
                SensorsConfiguration.Build.isMagnetometerDeactivated() ? "inactive" : "active");
    }

    private void initSubscriptions() {
        viewModel.getTimestamp().observe(getViewLifecycleOwner(),
                timestamp -> timeView.setText(timestamp));
        viewModel.getAccelerometerEvent().observe(getViewLifecycleOwner(),
                data -> accelerometerComponent.setSensorData(data));
        viewModel.getGyroscopeEvent().observe(getViewLifecycleOwner(),
                data -> gyroscopeComponent.setSensorData(data));
        viewModel.getGyroscopeEvent().observe(getViewLifecycleOwner(),
                data -> magnetometerComponent.setSensorData(data));
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