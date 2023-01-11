package michael.linker.gestureid.ui.fragment.sensors.root;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.config.sensor.SensorManagerConfiguration;
import michael.linker.gestureid.config.sensor.SensorsConfiguration;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.ui.view.composite.chart.ISensorChartView;
import michael.linker.gestureid.ui.view.composite.chart.SensorChartView;
import michael.linker.gestureid.ui.view.composite.chart.SensorChartViewData;
import michael.linker.gestureid.data.sensor.manager.ISensorManager;

public class SensorsFragment extends Fragment {
    private MaterialTextView timeView;
    private ISensorChartView accelerometerChartView, gyroscopeChartView, magnetometerChartView,
            gravityChartView, linearAccelerationChartView,
            rotationVectorChartView, geoRotationVectorChartView;
    private FloatingActionButton manageRecordingFab;

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
        initButtons();
        initSubscriptions();
    }

    private void initViews(View view) {
        timeView = view.findViewById(R.id.sensors_current_time);
        manageRecordingFab = view.findViewById(R.id.sensors_manage_recording_fab);
        accelerometerChartView = new SensorChartView(
                requireContext(),
                view.findViewById(R.id.sensors_accelerometer_chart),
                new SensorChartViewData(
                        BaseSensorType.ACCELEROMETER,
                        StringsProvider.getString(R.string.sensor_accelerometer),
                        StringsProvider.getString(R.string.sensor_accelerometer_not_available)
                ));
        gyroscopeChartView = new SensorChartView(
                requireContext(),
                view.findViewById(R.id.sensors_gyroscope_chart),
                new SensorChartViewData(
                        BaseSensorType.GYROSCOPE,
                        StringsProvider.getString(R.string.sensor_gyroscope),
                        StringsProvider.getString(R.string.sensor_gyroscope_not_available)
                )
        );
        magnetometerChartView = new SensorChartView(
                requireContext(),
                view.findViewById(R.id.sensors_magnetometer_chart),
                new SensorChartViewData(
                        BaseSensorType.MAGNETOMETER,
                        StringsProvider.getString(R.string.sensor_magnetometer),
                        StringsProvider.getString(R.string.sensor_magnetometer_not_available)
                )
        );
        gravityChartView = new SensorChartView(
                requireContext(),
                view.findViewById(R.id.sensors_gravity_chart),
                new SensorChartViewData(
                        CompositeSensorType.GRAVITY,
                        StringsProvider.getString(R.string.sensor_gravity),
                        StringsProvider.getString(R.string.sensor_gravity_not_available)
                )
        );
        linearAccelerationChartView = new SensorChartView(
                requireContext(),
                view.findViewById(R.id.sensors_linear_acceleration_chart),
                new SensorChartViewData(
                        CompositeSensorType.LINEAR_ACCELERATION,
                        StringsProvider.getString(R.string.sensor_linear_acceleration),
                        StringsProvider.getString(R.string.sensor_linear_acceleration_not_available)
                )
        );
        rotationVectorChartView = new SensorChartView(
                requireContext(),
                view.findViewById(R.id.sensors_rotation_vector_chart),
                new SensorChartViewData(
                        CompositeSensorType.ROTATION_VECTOR,
                        StringsProvider.getString(R.string.sensor_rotation_vector),
                        StringsProvider.getString(R.string.sensor_rotation_vector_not_available)
                )
        );
        geoRotationVectorChartView = new SensorChartView(
                requireContext(),
                view.findViewById(R.id.sensors_geo_rotation_vector_chart),
                new SensorChartViewData(
                        CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR,
                        StringsProvider.getString(R.string.sensor_geo_rotation_vector),
                        StringsProvider.getString(R.string.sensor_geo_rotation_vector_not_available)
                )
        );
    }

    private void initButtons() {
        manageRecordingFab.setOnClickListener(l -> {
            ISensorManager sensorManager = SensorManagerConfiguration.getManager();
            if (sensorManager.isRegisteringSuppressed()) {
                accelerometerChartView.clear();
                gyroscopeChartView.clear();
                magnetometerChartView.clear();
                gravityChartView.clear();
                linearAccelerationChartView.clear();
                rotationVectorChartView.clear();
                geoRotationVectorChartView.clear();
                sensorManager.unsuppressRegistering();
            } else {
                sensorManager.suppressRegistering();
            }
        });
    }

    private void initSubscriptions() {
        viewModel.getTimestamp().observe(getViewLifecycleOwner(),
                timestamp -> timeView.setText(timestamp));
        if (!SensorsConfiguration.Build.isAccelerometerDeactivated()) {
            accelerometerChartView.subscribeList(getViewLifecycleOwner(),
                    viewModel.getAccelerometerEvent());
        }
        if (!SensorsConfiguration.Build.isGyroscopeDeactivated()) {
            gyroscopeChartView.subscribeList(getViewLifecycleOwner(),
                    viewModel.getGyroscopeEvent());
        }
        if (!SensorsConfiguration.Build.isMagnetometerDeactivated()) {
            magnetometerChartView.subscribeList(getViewLifecycleOwner(),
                    viewModel.getMagnetometerEvent());
        }
        if (!SensorsConfiguration.Build.isGravityDeactivated()) {
            gravityChartView.subscribeList(getViewLifecycleOwner(), viewModel.getGravityEvent());
        }
        if (!SensorsConfiguration.Build.isLinearAccelerationDeactivated()) {
            linearAccelerationChartView.subscribeList(getViewLifecycleOwner(),
                    viewModel.getLinearAccelerationEvent());
        }
        if (!SensorsConfiguration.Build.isRotationVectorDeactivated()) {
            rotationVectorChartView.subscribeList(getViewLifecycleOwner(),
                    viewModel.getRotationVectorEvent());
        }
        if (!SensorsConfiguration.Build.isGeomagneticRotationVectorDeactivated()) {
            geoRotationVectorChartView.subscribeList(getViewLifecycleOwner(),
                    viewModel.getGeoRotationVectorEvent());
        }
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