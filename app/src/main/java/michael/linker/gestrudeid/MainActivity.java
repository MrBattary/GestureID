package michael.linker.gestrudeid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import michael.linker.gestrudeid.output.config.ISensorStreamConfiguration;
import michael.linker.gestrudeid.output.config.SensorStreamConfiguration;
import michael.linker.gestrudeid.output.stream.ISensorStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        TextView textView = findViewById(R.id.main_text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());
        ISensorStreamConfiguration outputConfiguration = new SensorStreamConfiguration(textView);
        ISensorStream sensorStream = outputConfiguration.getOutputStream();
        for (Sensor sensor : sensors) {
            sensorStream.write(sensor.toString());
        }

        // GyroscopeListener gyroscopeListener = new GyroscopeListener(sensorManager, textView);
        // sensorManager.registerListener(gyroscopeListener,  sensorManager.getDefaultSensor
        // (Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
    }
}