package michael.linker.gestureid.elements.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import michael.linker.gestureid.R;
import michael.linker.gestureid.elements.config.TestLoopConfiguration;
import michael.linker.gestureid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestureid.sensor.wrapper.manager.SensorManagerWrapper;
import michael.linker.gestureid.world.IWorld;
import michael.linker.gestureid.world.exception.WorldFailedException;
import michael.linker.gestureid.world.singleton.WorldSingleton;

public class MainActivity extends AppCompatActivity {
    private IWorld world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File directory = this.getExternalFilesDir(null);
        TestLoopConfiguration.setDestination(directory);
        TestLoopConfiguration.setTestLoopFolder(
                "/Test".concat(String.valueOf(System.currentTimeMillis())));

        buildButtons();

        SensorManager hardwareSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        ASensorManager sensorManager = new SensorManagerWrapper(hardwareSensorManager);

        try {
            WorldSingleton.initialize(sensorManager);
            world = WorldSingleton.getInstance();
        } catch (WorldFailedException e) {
            closeApplication();
        }
    }

    private void buildButtons() {
        Button start = findViewById(R.id.main__start);
        start.setOnClickListener(view -> {
            Intent activity = new Intent(MainActivity.this, ButtonActivity.class);
            activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(activity);
            finish();
        });

        Button exit = findViewById(R.id.main__exit);
        exit.setOnClickListener(view -> closeApplication());
    }

    private void closeApplication() {
        world.suppressRegistering();
        world.closeOutputStream();
        world.destroy();
        this.finishAndRemoveTask();
    }
}