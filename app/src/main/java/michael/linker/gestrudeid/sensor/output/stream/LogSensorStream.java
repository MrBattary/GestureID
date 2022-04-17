package michael.linker.gestrudeid.sensor.output.stream;

import android.util.Log;

/**
 * This stream uses logs to write the data
 * Current log level - INFO
 */
public class LogSensorStream implements ISensorStream {
    private final static String TAG = "SENSOR STREAM";

    @Override
    public void write(String s) {
        Log.i(TAG, s);
    }
}
