package michael.linker.gestureid.sensor.listener.base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import michael.linker.gestureid.sensor.listener.ISensorListener;
import michael.linker.gestureid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestureid.sensor.model.base.MagneticFieldSensorModel;
import michael.linker.gestureid.sensor.type.BaseSensorType;
import michael.linker.gestureid.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestureid.synchronizer.IEventSynchronizer;

public class MagneticFieldSensorListener implements ISensorListener {
    private static final String TAG = MagneticFieldSensorListener.class.getCanonicalName();
    private final IEventSynchronizer eventSynchronizer;
    private final ISensorListenerSuppressor listenerSuppressor;

    public MagneticFieldSensorListener(final IEventSynchronizer eventSynchronizer,
                                       final ISensorListenerSuppressor listenerSuppressor) {
        this.eventSynchronizer = eventSynchronizer;
        this.listenerSuppressor = listenerSuppressor;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            final boolean isThisListenerSuppressed = listenerSuppressor.isListenerSuppressed(
                    BaseSensorType.MAGNETOMETER);
            if (!listenerSuppressor.isAllListenersSuppressed()) {
                if (!isThisListenerSuppressed) {
                    proceedEvent(sensorEvent);
                }
            }
            // If this listener is not registered in the ListenerSuppressor
        } catch (SensorListenerSuppressorNotFoundException e) {
            proceedEvent(sensorEvent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void proceedEvent(SensorEvent sensorEvent) {
        MagneticFieldSensorModel sensorModel = new MagneticFieldSensorModel();
        sensorModel.setX(sensorEvent.values[0]);
        sensorModel.setY(sensorEvent.values[1]);
        sensorModel.setZ(sensorEvent.values[2]);
        sensorModel.setTimestamp(sensorEvent.timestamp);
        try {
            eventSynchronizer.registerEvent(sensorModel);
        } catch (EventSynchronizerFailedException e) {
            Log.w(TAG, e.getMessage());
        }
    }
}
