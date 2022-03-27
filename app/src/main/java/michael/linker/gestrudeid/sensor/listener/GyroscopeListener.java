package michael.linker.gestrudeid.sensor.listener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import michael.linker.gestrudeid.sensor.listener.exception.SensorNotFoundException;

/**
 * Gyroscope listener
 */
public class GyroscopeListener implements SensorEventListener {
    private static final String TAG = GyroscopeListener.class.getCanonicalName();
    private TextView outer;
    private Sensor gyroscopeSensor = null;
    private final SensorManager sensorManager;

    public GyroscopeListener(SensorManager sensorManager, TextView outer) throws SensorNotFoundException {
        this.sensorManager = sensorManager;
        this.outer = outer;
        gyroscopeSensor = this.sensorManager.getDefaultSensor(Sensor.REPORTING_MODE_SPECIAL_TRIGGER);
        if (gyroscopeSensor == null) {
            throw new SensorNotFoundException("Gyroscope sensor not found!");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        outer.append("Orientation X (Roll) :"+ sensorEvent.values[2] +"\n"+
                "Orientation Y (Pitch) :"+ sensorEvent.values[1] +"\n"+
                "Orientation Z (Yaw) :"+ sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
