package michael.linker.gestureid.data.sensor.listener.composite;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import michael.linker.gestureid.config.event.EventSynchronizerConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerSuppressorConfiguration;
import michael.linker.gestureid.data.event.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestureid.data.event.synchronizer.IEventSynchronizer;
import michael.linker.gestureid.data.sensor.listener.ISensorListener;
import michael.linker.gestureid.data.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.data.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestureid.data.sensor.model.composite.RotationVectorSensorModel;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;

public class RotationVectorSensorListener implements ISensorListener {
    private static final String TAG = RotationVectorSensorListener.class.getCanonicalName();
    private final IEventSynchronizer eventSynchronizer;
    private final ISensorListenerSuppressor listenerSuppressor;

    public RotationVectorSensorListener() {
        this.eventSynchronizer = EventSynchronizerConfiguration.getEventSynchronizer();
        this.listenerSuppressor = SensorListenerSuppressorConfiguration.getSensorListenerSuppressor();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            final boolean isThisListenerSuppressed = listenerSuppressor.isListenerSuppressed(
                    CompositeSensorType.ROTATION_VECTOR);
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
        RotationVectorSensorModel sensorModel = new RotationVectorSensorModel();
        sensorModel.setX((double) sensorEvent.values[0]);
        sensorModel.setY((double) sensorEvent.values[1]);
        sensorModel.setZ((double) sensorEvent.values[2]);
        sensorModel.setW((double) sensorEvent.values[3]);
        sensorModel.setTimestamp((double)sensorEvent.timestamp);
        try {
            eventSynchronizer.registerEvent(sensorModel);
        } catch (EventSynchronizerFailedException e) {
            Log.w(TAG, e.getMessage());
        }
    }
}
