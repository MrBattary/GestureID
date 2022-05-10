package michael.linker.gestrudeid;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.stream.Collectors;

import michael.linker.gestrudeid.sensor.listener.primitive.base.AccelerometerSensorListener;
import michael.linker.gestrudeid.sensor.listener.primitive.base.GyroscopeSensorListener;
import michael.linker.gestrudeid.sensor.listener.primitive.base.MagneticFieldSensorListener;
import michael.linker.gestrudeid.sensor.core.provider.ISensorProvider;
import michael.linker.gestrudeid.sensor.core.provider.SensorProvider;
import michael.linker.gestrudeid.sensor.types.BaseSensorType;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;
import michael.linker.gestrudeid.streams.manager.IStreamManager;
import michael.linker.gestrudeid.streams.manager.StreamManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        TextView textView = findViewById(R.id.main_text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());

        IStreamManager streamManager = new StreamManager(textView);
        IOutputStream outputStream = streamManager.getOutputStream();

        ISensorProvider sensorProvider = new SensorProvider(sensorManager);
        outputStream.write("\nAll sensors:\n" + sensorProvider.
                getSensors().
                stream().
                map(sensor -> sensor.getName() + "\n")
                .collect(Collectors.joining()));
        outputStream.write("\nActivated sensors:\n" + sensorProvider.
                getActivatedSensors().
                stream().
                map(sensor -> sensor.getName() + "\n")
                .collect(Collectors.joining()));

        final int delay = SensorManager.SENSOR_DELAY_NORMAL;
        sensorManager.registerListener(new AccelerometerSensorListener(outputStream),
                sensorProvider.getSensor(BaseSensorType.ACCELEROMETER), delay);
        sensorManager.registerListener(new GyroscopeSensorListener(outputStream),
                sensorProvider.getSensor(BaseSensorType.GYROSCOPE), delay);
        sensorManager.registerListener(new MagneticFieldSensorListener(outputStream),
                sensorProvider.getSensor(BaseSensorType.MAGNETOMETER), delay);

        // GyroscopeListener gyroscopeListener = new GyroscopeListener(sensorManager, textView);
        // sensorManager.registerListener(gyroscopeListener,  sensorManager.getDefaultSensor
        // (Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
    }
}