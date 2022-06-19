package michael.linker.gestureid.stream.output.factory.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import michael.linker.gestureid.config.StreamsBuildConfiguration;
import michael.linker.gestureid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestureid.stream.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestureid.stream.output.model.FileOutputModel;
import michael.linker.gestureid.stream.output.stream.IOutputStream;
import michael.linker.gestureid.stream.output.stream.impl.FileOutputStream;

/**
 * The file stream factory
 */
public class FileOutputStreamFactory implements IOutputStreamFactory {
    private final FileOutputModel fileOutputModel;
    private FileOutputStream fileOutputStream;

    public FileOutputStreamFactory(FileOutputModel fileOutputModel) {
        this.fileOutputModel = fileOutputModel;
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
        File directory = new File(fileOutputModel.getDestination(),
                StreamsBuildConfiguration
                        .getFileOutputDirectory()
                        .concat(fileOutputModel.getPath()));
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new OutputStreamFactoryFailedException(
                        "The output directory cannot be created!");
            }
        }

        // Create file
        File file = new File(directory, fileOutputModel.getFilename());
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
