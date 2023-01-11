package michael.linker.gestureid.data.sensor.listener.base;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import michael.linker.gestureid.config.event.EventSynchronizerConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerConfiguration;
import michael.linker.gestureid.data.event.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestureid.data.event.synchronizer.IEventSynchronizer;
import michael.linker.gestureid.data.sensor.listener.ISensorListener;
import michael.linker.gestureid.data.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.data.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestureid.data.sensor.model.base.AccelerometerSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;

public class AccelerometerSensorListener implements ISensorListener {
    private static final String TAG = AccelerometerSensorListener.class.getCanonicalName();
    private final IEventSynchronizer eventSynchronizer;
    private final ISensorListenerSuppressor listenerSuppressor;

    public AccelerometerSensorListener() {
        this.eventSynchronizer = EventSynchronizerConfiguration.getEventSynchronizer();
        this.listenerSuppressor = SensorListenerConfiguration.getSensorListenerSuppressor();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            final boolean isThisListenerSuppressed = listenerSuppressor.isListenerSuppressed(
                    BaseSensorType.ACCELEROMETER);
            if (!isThisListenerSuppressed) {
                proceedEvent(sensorEvent);
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