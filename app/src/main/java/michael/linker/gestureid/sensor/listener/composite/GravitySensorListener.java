package michael.linker.gestureid.sensor.listener.composite;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import michael.linker.gestureid.sensor.listener.ISensorListener;
import michael.linker.gestureid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestureid.sensor.model.composite.GravitySensorModel;
import michael.linker.gestureid.sensor.type.CompositeSensorType;
import michael.linker.gestureid.event.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestureid.event.synchronizer.IEventSynchronizer;

public class GravitySensorListener implements ISensorListener {
    private static final String TAG = GravitySensorListener.class.getCanonicalName();
    private final IEventSynchronizer eventSynchronizer;
    private final ISensorListenerSuppressor listenerSuppressor;

    public GravitySensorListener(final IEventSynchronizer eventSynchronizer,
            final ISensorListenerSuppressor listenerSuppressor) {
        this.eventSynchronizer = eventSynchronizer;
        this.listenerSuppressor = listenerSuppressor;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            final boolean isThisListenerSuppressed = listenerSuppressor.isListenerSuppressed(
                    CompositeSensorType.GRAVITY);
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
        GravitySensorModel sensorModel = new GravitySensorModel();
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
