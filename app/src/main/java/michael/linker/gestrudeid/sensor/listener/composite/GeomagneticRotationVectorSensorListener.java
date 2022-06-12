package michael.linker.gestrudeid.sensor.listener.composite;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestrudeid.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestrudeid.sensor.model.composite.GeomagneticRotationVectorSensorModel;
import michael.linker.gestrudeid.sensor.type.CompositeSensorType;
import michael.linker.gestrudeid.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestrudeid.synchronizer.IEventSynchronizer;

public class GeomagneticRotationVectorSensorListener implements ISensorListener {
    private static final String TAG =
            GeomagneticRotationVectorSensorListener.class.getCanonicalName();
    private final IEventSynchronizer eventSynchronizer;
    private final ISensorListenerSuppressor listenerSuppressor;

    public GeomagneticRotationVectorSensorListener(final IEventSynchronizer eventSynchronizer,
            final ISensorListenerSuppressor listenerSuppressor) {
        this.eventSynchronizer = eventSynchronizer;
        this.listenerSuppressor = listenerSuppressor;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            final boolean isThisListenerSuppressed = listenerSuppressor.isListenerSuppressed(
                    CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR);
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
        GeomagneticRotationVectorSensorModel sensorModel = new GeomagneticRotationVectorSensorModel();
        sensorModel.setX(sensorEvent.values[0]);
        sensorModel.setY(sensorEvent.values[1]);
        sensorModel.setZ(sensorEvent.values[2]);
        sensorModel.setW(sensorEvent.values[3]);
        sensorModel.setTimestamp(sensorEvent.timestamp);
        try {
            eventSynchronizer.registerEvent(sensorModel);
        } catch (EventSynchronizerFailedException e) {
            Log.w(TAG, e.getMessage());
        }
    }
}