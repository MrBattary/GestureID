package michael.linker.gestureid.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import michael.linker.gestureid.R;
import michael.linker.gestureid.config.Configuration;
import michael.linker.gestureid.config.ConfigurationChain;
import michael.linker.gestureid.config.event.EventAccumulatorConfiguration;
import michael.linker.gestureid.config.sensor.SensorManagerConfiguration;
import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.data.res.StringsProvider;
import michael.linker.gestureid.data.sensor.manager.ISensorManager;
import michael.linker.gestureid.databinding.ActivityMainBinding;
import michael.linker.gestureid.ui.view.elementary.dialog.IDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.two.TwoChoicesDialog;
import michael.linker.gestureid.ui.view.elementary.dialog.two.TwoChoicesDialogModel;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    private ISensorManager manager;

    private IDialog exitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();
        initDialogs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSensorManager();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (!getOnBackPressedDispatcher().hasEnabledCallbacks()) {
            exitDialog.show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        manager.destroy();
        super.onPause();
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

    private void initDialogs() {
        exitDialog = new TwoChoicesDialog(
                this,
                new TwoChoicesDialogModel(
                        StringsProvider.getString(R.string.dialog_exit_title),
                        StringsProvider.getString(R.string.dialog_exit_text),
                        StringsProvider.getString(R.string.button_exit),
                        StringsProvider.getString(R.string.button_cancel)),
                (dialogInterface, i) -> {
                    exitDialog.dismiss();
                    ActivityGate.finishApplication(this);
                },
                (dialogInterface, i) -> exitDialog.dismiss()
        );
    }

    private void initSensorManager() {
        Configuration.updateConfiguration(
                new ConfigurationChain(
                        EventAccumulatorConfiguration.Type.ACTIVE_DISTRIBUTABLE,
                        SystemConfiguration.Type.Status.DISABLED
                ));
        manager = SensorManagerConfiguration.getManager();
        manager.suppressRegistering();
    }
}