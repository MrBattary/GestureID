package michael.linker.gestureid.elements.activity;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import michael.linker.gestureid.R;
import michael.linker.gestureid.databinding.ActivityMainBinding;
import michael.linker.gestureid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestureid.sensor.wrapper.manager.SensorManagerWrapper;
import michael.linker.gestureid.world.IWorld;
import michael.linker.gestureid.world.exception.WorldFailedException;
import michael.linker.gestureid.world.singleton.WorldSingleton;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    private IWorld world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();
        initWorld();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        world.destroy();
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

    private AppBarConfiguration buildAppBarConfiguration() {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        return new AppBarConfiguration.Builder(
                R.id.navigation_sensors,
                R.id.navigation_home/*,
                R.id.navigation_playground*/).build();
    }

    private void initWorld() {
        SensorManager hardwareSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        ASensorManager sensorManager = new SensorManagerWrapper(hardwareSensorManager);

        try {
            WorldSingleton.initialize(sensorManager);
            world = WorldSingleton.getInstance();
            world.suppressRegistering();
        } catch (WorldFailedException e) {
            closeApplication();
        }
    }

    private void closeApplication() {
        world.destroy();
        ActivityGate.finishApplication(this);
    }
}