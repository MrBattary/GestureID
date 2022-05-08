package michael.linker.gestrudeid.sensor.listener.base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;

public class GyroscopeSensorListener implements ISensorListener {
    private final IOutputStream outputStream;

    public GyroscopeSensorListener(IOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        outputStream.write("Orientation X (Roll) :" + sensorEvent.values[2] + "\n" +
                "Orientation Y (Pitch) :" + sensorEvent.values[1] + "\n" +
                "Orientation Z (Yaw) :" + sensorEvent.values[0] + "\n");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
