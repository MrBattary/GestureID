package michael.linker.gestureid.data.stream.output.file;

import java.nio.file.Path;

import michael.linker.gestureid.data.stream.output.IOutputStream;

/**
 * General output stream class for the file
 */
public interface IFileOutputStream extends IOutputStream {
    /**
     * Get output filepath.
     *
     * @return - FilePath as Path.
     */
    Path getOutputFilePath();
}
