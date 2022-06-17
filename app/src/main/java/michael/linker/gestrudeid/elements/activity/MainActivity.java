package michael.linker.gestrudeid.elements.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import michael.linker.gestrudeid.R;
import michael.linker.gestrudeid.elements.config.TestLoopConfiguration;
import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.wrapper.manager.SensorManagerWrapper;
import michael.linker.gestrudeid.world.IWorld;
import michael.linker.gestrudeid.world.exception.WorldFailedException;
import michael.linker.gestrudeid.world.singleton.WorldSingleton;

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
        world.destroy();
        this.finishAndRemoveTask();
    }
}