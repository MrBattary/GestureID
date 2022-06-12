package michael.linker.gestrudeid.stream.output.stream.impl;

import android.util.Log;

import java.io.FileWriter;
import java.io.IOException;

import michael.linker.gestrudeid.stream.output.stream.IOutputStream;

/**
 * This stream uses internal storage and text file to record data
 */
public class FileOutputStream implements IOutputStream {
    private final static String TAG = FileOutputStream.class.getCanonicalName();
    private final FileWriter writer;

    public FileOutputStream(FileWriter fileWriter) {
        this.writer = fileWriter;
    }

    @Override
    public void write(String s) {
        try {
            writer.write(s);
            writer.flush();
        } catch (IOException e) {
            Log.e(TAG,e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            Log.e(TAG,e.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
