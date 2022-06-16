package michael.linker.gestrudeid.elements.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import michael.linker.gestrudeid.R;

public class MainActivity extends AppCompatActivity {
    Button start;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        start = findViewById(R.id.main__start);
        start.setOnClickListener(view -> {
            Intent activity = new Intent(MainActivity.this, ButtonActivity.class);
            activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(activity);
            finish();
        });

        exit = findViewById(R.id.main__exit);
        exit.setOnClickListener(view -> closeApplication());

        /*SensorManager hardwareSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        ASensorManager sensorManager = new SensorManagerWrapper(hardwareSensorManager);

        File directory = this.getExternalFilesDir(null);
        String path = "";*/
        /*
        try {
            IWorld world = WorldSingleton.getInstance(sensorManager);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    world.setNewOutputStream(
                            new FileOutputModel(directory, path, "OutputFirst.txt"));
                    world.unsuppressRegistering();
                }
            }, 5000);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    world.suppressRegistering();
                    world.setNewOutputStream(
                            new FileOutputModel(directory, path, "OutputSecond.txt"));
                    world.unsuppressRegistering();
                }
            }, 10000);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    world.suppressRegistering();
                    world.destroy();
                    closeApplication();
                }
            }, 15000);
        } catch (WorldFailedException e) {
            e.getStackTrace();
        }*/
    }

    private void closeApplication() {
        this.finishAffinity();
    }
}