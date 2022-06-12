package michael.linker.gestrudeid.sensor.listener.base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestrudeid.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestrudeid.sensor.model.base.AccelerometerSensorModel;
import michael.linker.gestrudeid.sensor.type.BaseSensorType;
import michael.linker.gestrudeid.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestrudeid.synchronizer.IEventSynchronizer;

public class AccelerometerSensorListener implements ISensorListener {
    private static final String TAG = AccelerometerSensorListener.class.getCanonicalName();
    private final IEventSynchronizer eventSynchronizer;
    private final ISensorListenerSuppressor listenerSuppressor;

    public AccelerometerSensorListener(final IEventSynchronizer eventSynchronizer,
            final ISensorListenerSuppressor listenerSuppressor) {
        this.eventSynchronizer = eventSynchronizer;
        this.listenerSuppressor = listenerSuppressor;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            final boolean isThisListenerSuppressed = listenerSuppressor.isListenerSuppressed(
                    BaseSensorType.ACCELEROMETER);
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
        AccelerometerSensorModel sensorModel = new AccelerometerSensorModel();
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