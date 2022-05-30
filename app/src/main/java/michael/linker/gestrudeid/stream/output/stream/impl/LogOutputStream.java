package michael.linker.gestrudeid.stream.output.stream.impl;

import android.util.Log;

import michael.linker.gestrudeid.stream.output.stream.IOutputStream;

/**
 * This stream uses internal logger to record data
 * Current log level - INFO
 */
public class LogOutputStream implements IOutputStream {
    private final static String TAG = "SENSOR STREAM";

    @Override
    public void write(String s) {
        Log.i(TAG, s);
    }
}
