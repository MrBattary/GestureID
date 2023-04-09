package michael.linker.gestureid.analyzer.addons.file.input;

import michael.linker.gestureid.analyzer.addons.file.IFile;
import michael.linker.gestureid.analyzer.addons.file.input.exception.InputFileReadingFailedException;

import java.util.List;

public interface IInputFile extends IFile {
    /**
     * Read all lines from the file to the single String.
     *
     * @return List of all lines from file.
     * @throws InputFileReadingFailedException If reading from the file failed.
     */
    List<String> readAllLines() throws InputFileReadingFailedException;
}
