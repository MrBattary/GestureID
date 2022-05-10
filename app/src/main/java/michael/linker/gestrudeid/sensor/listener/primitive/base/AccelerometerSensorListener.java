package michael.linker.gestrudeid.sensor.listener.primitive.base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import java.util.Arrays;

import michael.linker.gestrudeid.sensor.listener.primitive.ISensorListener;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;

public class AccelerometerSensorListener implements ISensorListener {
    private final IOutputStream outputStream;

    public AccelerometerSensorListener(IOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        outputStream.write(Arrays.toString(sensorEvent.values));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
