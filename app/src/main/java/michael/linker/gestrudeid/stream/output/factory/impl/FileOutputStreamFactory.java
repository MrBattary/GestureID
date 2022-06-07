package michael.linker.gestrudeid.stream.output.factory.impl;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import michael.linker.gestrudeid.config.StreamsBuildConfiguration;
import michael.linker.gestrudeid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestrudeid.stream.output.stream.IOutputStream;
import michael.linker.gestrudeid.stream.output.stream.impl.FileOutputStream;

/**
 * The file stream factory
 */
public class FileOutputStreamFactory implements IOutputStreamFactory {
    private final String filename;
    private final Context context;
    private FileOutputStream fileOutputStream;

    public FileOutputStreamFactory(Context context, String filename) {
        this.filename = filename;
        this.context = context;
    }

    @Override
    public IOutputStream getOutputStream() throws OutputStreamFactoryFailedException {
        if (fileOutputStream == null) {
            fileOutputStream = new FileOutputStream(openFileForWriting());
        }
        return fileOutputStream;
    }

    private FileWriter openFileForWriting() throws OutputStreamFactoryFailedException {
        // Create directory
        File directory = new File(context.getExternalFilesDir(null),
                StreamsBuildConfiguration.getFileOutputDirectory());
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new OutputStreamFactoryFailedException(
                        "The output directory cannot be created!");
            }
        }

        // Create file
        File file = new File(directory, filename);
        if (!file.canWrite()) {
            try {
                if (!file.createNewFile()) {
                    throw new IOException("The output file cannot be created!");
                }
            } catch (IOException e) {
                throw new OutputStreamFactoryFailedException(e.getMessage());
            }
        }

        // Open file for writing
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            throw new OutputStreamFactoryFailedException(e.getMessage());
        }
    }
}
