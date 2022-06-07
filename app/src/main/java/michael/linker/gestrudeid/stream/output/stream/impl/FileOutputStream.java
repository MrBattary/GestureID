package michael.linker.gestrudeid.stream.output.stream.impl;

import java.io.FileWriter;
import java.io.IOException;

import michael.linker.gestrudeid.stream.output.stream.IOutputStream;

/**
 * This stream uses internal storage and text file to record data
 */
public class FileOutputStream implements IOutputStream {
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
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        writer.close();
        super.finalize();
    }
}
