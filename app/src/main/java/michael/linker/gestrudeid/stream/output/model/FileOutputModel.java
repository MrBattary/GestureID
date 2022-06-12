package michael.linker.gestrudeid.stream.output.model;

import java.io.File;

import michael.linker.gestrudeid.stream.output.type.OutputStreamType;

/**
 * The model used by the Output File Stream components
 */
public class FileOutputModel extends AOutputStreamModel {
    private final File destination;
    private final String path;
    private final String filename;

    /**
     * Default constructor
     *
     * @param destination File from Context by getExternalFilesDir(...)
     * @param path Path to folder/s, example: "", "/Folder", "/Folder/InnerFolder"
     * @param filename Name of file
     */
    public FileOutputModel(File destination, String path, String filename) {
        super(OutputStreamType.FILE);
        this.destination = destination;
        this.path = path;
        this.filename = filename;
    }

    public File getDestination() {
        return destination;
    }

    public String getPath() {
        return path;
    }

    public String getFilename() {
        return filename;
    }
}
