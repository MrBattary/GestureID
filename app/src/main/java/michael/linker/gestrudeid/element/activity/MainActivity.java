package michael.linker.gestrudeid.element.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Test text");
        builder.setTitle("Test dialog");
        builder.setPositiveButton("Close", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            Intent intent = new Intent(MainActivity.this, SwipeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        /*SensorManager hardwareSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        ASensorManager sensorManager = new SensorManagerWrapper(hardwareSensorManager);

        File directory = this.getExternalFilesDir(null);
        String path = "";

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
                    exit();
                }
            }, 15000);
        } catch (WorldFailedException e) {
            e.getStackTrace();
        }*/
    }

    private void exit() {
        this.finishAffinity();
    }
}