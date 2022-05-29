package michael.linker.gestrudeid.sensor.listener.base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import java.util.Arrays;

import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.sensor.model.base.AccelerometerSensorModel;
import michael.linker.gestrudeid.synchronizer.IEventSynchronizer;

public class AccelerometerSensorListener implements ISensorListener {
    private final IEventSynchronizer eventSynchronizer;

    public AccelerometerSensorListener(IEventSynchronizer eventSynchronizer) {
        this.eventSynchronizer = eventSynchronizer;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        AccelerometerSensorModel sensorModel = new AccelerometerSensorModel();
        eventSynchronizer.registerEvent();
        eventSynchronizer.write(Arrays.toString(sensorEvent.values));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
