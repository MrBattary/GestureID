package michael.linker.gestrudeid;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.wrapper.manager.SensorManagerWrapper;
import michael.linker.gestrudeid.stream.output.model.FileOutputModel;
import michael.linker.gestrudeid.stream.output.model.UiOutputModel;
import michael.linker.gestrudeid.world.IWorld;
import michael.linker.gestrudeid.world.exception.WorldFailedException;
import michael.linker.gestrudeid.world.singleton.WorldSingleton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager hardwareSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        ASensorManager sensorManager = new SensorManagerWrapper(hardwareSensorManager);

        TextView textView = this.findViewById(R.id.main_text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());

        File directory = this.getExternalFilesDir(null);
        String path = "";

        UiOutputModel uiOutputModel = new UiOutputModel(textView);

        try {
            IWorld world = WorldSingleton.getInstance(sensorManager);
            world.setNewOutputStream(uiOutputModel);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    world.unsuppressRegistering();
                }
            }, 5000);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    world.suppressRegistering();
                    world.setNewOutputStream(
                            new FileOutputModel(directory, path, "OutputFirst.txt"));
                    world.unsuppressRegistering();
                }
            }, 10000);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    world.suppressRegistering();
                    world.setNewOutputStream(
                            new FileOutputModel(directory, path, "OutputSecond.txt"));
                    world.unsuppressRegistering();
                }
            }, 15000);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    world.suppressRegistering();
                    world.destroy();
                    exit();
                }
            }, 20000);
        } catch (WorldFailedException e) {
            e.getStackTrace();
        }
    }

    private void exit() {
        this.finishAffinity();
    }
}