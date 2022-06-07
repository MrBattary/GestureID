package michael.linker.gestrudeid;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import michael.linker.gestrudeid.formatter.IFormatter;
import michael.linker.gestrudeid.formatter.factory.FormatterFactory;
import michael.linker.gestrudeid.formatter.factory.IFormatterFactory;
import michael.linker.gestrudeid.sensor.listener.manager.ISensorListenerManager;
import michael.linker.gestrudeid.sensor.listener.manager.SensorListenerManager;
import michael.linker.gestrudeid.sensor.listener.provider.ISensorListenerProvider;
import michael.linker.gestrudeid.sensor.listener.provider.SensorListenerProvider;
import michael.linker.gestrudeid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestrudeid.sensor.listener.suppressor.SensorListenerSuppressor;
import michael.linker.gestrudeid.sensor.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.manager.SensorManagerWrapper;
import michael.linker.gestrudeid.sensor.provider.ISensorProvider;
import michael.linker.gestrudeid.sensor.provider.SensorProvider;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.stream.manager.IStreamManager;
import michael.linker.gestrudeid.stream.manager.StreamManager;
import michael.linker.gestrudeid.stream.output.stream.IOutputStream;
import michael.linker.gestrudeid.synchronizer.EventSynchronizer;
import michael.linker.gestrudeid.synchronizer.IEventSynchronizer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager hardwareSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        ASensorManager sensorManager = new SensorManagerWrapper(hardwareSensorManager);

        TextView textView = findViewById(R.id.main_text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());

        IStreamManager streamManager = new StreamManager(textView, this, "First.txt");
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

        IFormatterFactory formatterFactory = new FormatterFactory(outputStream);
        IFormatter formatter = formatterFactory.getFormatter();

        IEventSynchronizer eventSynchronizer = new EventSynchronizer(formatter);

        ISensorListenerSuppressor sensorListenerSuppressor = new SensorListenerSuppressor();
        ISensorListenerProvider sensorListenerProvider = new SensorListenerProvider(
                eventSynchronizer, sensorListenerSuppressor);

        eventSynchronizer.attachOneListener(BaseSensorType.ACCELEROMETER);
        eventSynchronizer.attachOneListener(BaseSensorType.GYROSCOPE);
        eventSynchronizer.attachOneListener(BaseSensorType.MAGNETOMETER);

        ISensorListenerManager sensorListenerManager = new SensorListenerManager(
                sensorListenerProvider, sensorManager, sensorProvider);
        sensorListenerManager.registerListener(BaseSensorType.ACCELEROMETER);
        sensorListenerManager.registerListener(BaseSensorType.GYROSCOPE);
        sensorListenerManager.registerListener(BaseSensorType.MAGNETOMETER);

        sensorListenerSuppressor.unsuppressListener(BaseSensorType.ACCELEROMETER);
        sensorListenerSuppressor.unsuppressListener(BaseSensorType.GYROSCOPE);
        sensorListenerSuppressor.unsuppressListener(BaseSensorType.MAGNETOMETER);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                sensorListenerSuppressor.unsuppressAllListeners();
            }
        }, 5000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                sensorListenerSuppressor.suppressAllListeners();
                sensorListenerManager.unregisterAllListeners();
            }
        }, 10000);
    }
}