package michael.linker.gestureid.elements.activity;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import michael.linker.gestureid.R;
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

    private void closeApplication() {
        world.destroy();
        ActivityGate.finishApplication(this);
    }
}