package michael.linker.gestrudeid;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.stream.Collectors;

import michael.linker.gestrudeid.sensor.listener.base.AccelerometerSensorListener;
import michael.linker.gestrudeid.sensor.listener.base.GyroscopeSensorListener;
import michael.linker.gestrudeid.sensor.listener.base.MagneticFieldSensorListener;
import michael.linker.gestrudeid.sensor.provider.ISensorsProvider;
import michael.linker.gestrudeid.sensor.provider.SensorsProvider;
import michael.linker.gestrudeid.sensor.types.BaseSensorType;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;
import michael.linker.gestrudeid.streams.provider.IStreamsProvider;
import michael.linker.gestrudeid.streams.provider.StreamsProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        TextView textView = findViewById(R.id.main_text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());

        IStreamsProvider outputConfiguration = new StreamsProvider(textView);
        IOutputStream outputStream = outputConfiguration.getOutputStream();

        ISensorsProvider sensorsProvider = new SensorsProvider(sensorManager);
        outputStream.write("\nAll sensors:\n" + sensorsProvider.
                getSensors().
                stream().
                map(sensor -> sensor.getName() + "\n")
                .collect(Collectors.joining()));
        outputStream.write("\nActivated sensors:\n" + sensorsProvider.
                getActivatedSensors().
                stream().
                map(sensor -> sensor.getName() + "\n")
                .collect(Collectors.joining()));

        final int delay = SensorManager.SENSOR_DELAY_NORMAL;
        sensorManager.registerListener(new AccelerometerSensorListener(outputStream),
                sensorsProvider.getSensor(BaseSensorType.ACCELEROMETER), delay);
        sensorManager.registerListener(new GyroscopeSensorListener(outputStream),
                sensorsProvider.getSensor(BaseSensorType.GYROSCOPE), delay);
        sensorManager.registerListener(new MagneticFieldSensorListener(outputStream),
                sensorsProvider.getSensor(BaseSensorType.MAGNETOMETER), delay);

        // GyroscopeListener gyroscopeListener = new GyroscopeListener(sensorManager, textView);
        // sensorManager.registerListener(gyroscopeListener,  sensorManager.getDefaultSensor
        // (Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
    }
}