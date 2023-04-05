package michael.linker.gestureid.analyzer.file.input;

import michael.linker.gestureid.analyzer.file.IFile;
import michael.linker.gestureid.analyzer.file.input.exception.InputFileReadingFailedException;

public interface IInputFile extends IFile {
    String readAllLines() throws InputFileReadingFailedException;
}
