package michael.linker.gestrudeid.output.stream.internal;

import android.util.Log;

import michael.linker.gestrudeid.output.stream.ISensorStream;

/**
 * This stream uses internal logger to record data
 * Current log level - INFO
 */
public class LogSensorStream implements ISensorStream {
    private final static String TAG = "SENSOR STREAM";

    @Override
    public void write(String s) {
        Log.i(TAG, s);
    }
}
