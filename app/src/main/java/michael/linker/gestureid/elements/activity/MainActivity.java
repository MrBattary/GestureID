package michael.linker.gestureid.elements.activity;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.config.sensor.SensorManagerConfiguration;
import michael.linker.gestureid.databinding.ActivityMainBinding;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveFlushableEventAccumulator;
import michael.linker.gestureid.sensor.manager.ISensorManager;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    private ISensorManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();
        initSensorManager();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.destroy();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                ((IActiveFlushableEventAccumulator) EventAccumulatorConfiguration.getActiveAccumulator()).startAccumulation();
                break;
            case (MotionEvent.ACTION_UP):
                ((IActiveFlushableEventAccumulator) EventAccumulatorConfiguration.getActiveAccumulator()).flush();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private void initNavigation() {
        BottomNavigationView bottomNavigationView = this.findViewById(R.id.bottom_navigation);
        navController = findNavController(this);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    private NavController findNavController(final AppCompatActivity activity)
            throws RuntimeException {
        Fragment fragment = activity.getSupportFragmentManager().findFragmentById(
                R.id.main_navigation_host_fragment);
        if (!(fragment instanceof NavHostFragment)) {
            throw new RuntimeException();
        }
        return ((NavHostFragment) fragment).getNavController();
    }

    private void initSensorManager() {
        EventAccumulatorConfiguration.getFlushableActiveAccumulator();
        manager = SensorManagerConfiguration.getManager();
        manager.suppressRegistering();
    }
}