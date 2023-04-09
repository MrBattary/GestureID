package michael.linker.gestureid.analyzer.addons.file.output;

import michael.linker.gestureid.analyzer.addons.file.IFile;
import michael.linker.gestureid.analyzer.addons.file.output.exception.OutputFileWritingFailedException;

public interface IOutputFile extends IFile {
    /**
     * Write line to file.
     *
     * @param line The line that will be written to the file.
     * @throws OutputFileWritingFailedException If the writing failed.
     */
    void writeLine(String line) throws OutputFileWritingFailedException;
}
