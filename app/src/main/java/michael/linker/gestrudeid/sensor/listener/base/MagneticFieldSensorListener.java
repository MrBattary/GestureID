package michael.linker.gestrudeid.sensor.listener.base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.sensor.model.base.MagneticFieldSensorModel;
import michael.linker.gestrudeid.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestrudeid.synchronizer.IEventSynchronizer;

public class MagneticFieldSensorListener implements ISensorListener {
    private final static String TAG = MagneticFieldSensorListener.class.getCanonicalName();
    private final IEventSynchronizer eventSynchronizer;

    public MagneticFieldSensorListener(IEventSynchronizer eventSynchronizer) {
        this.eventSynchronizer = eventSynchronizer;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        MagneticFieldSensorModel sensorModel = new MagneticFieldSensorModel();
        sensorModel.setX(sensorEvent.values[0]);
        sensorModel.setY(sensorEvent.values[1]);
        sensorModel.setZ(sensorEvent.values[2]);
        sensorModel.setTimestamp(sensorEvent.timestamp);
        try {
            eventSynchronizer.registerEvent(sensorModel);
        } catch (EventSynchronizerFailedException e){
            Log.w(TAG, e.getMessage());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
