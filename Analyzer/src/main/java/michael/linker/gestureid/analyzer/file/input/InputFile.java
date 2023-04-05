package michael.linker.gestureid.analyzer.file.input;

import michael.linker.gestureid.analyzer.file.input.exception.InputFileReadingFailedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputFile implements IInputFile {
    private static final String READ_ALL_LINES_DELIMITER = "";
    private final Path filePath;

    public InputFile(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public String readAllLines() throws InputFileReadingFailedException {
        try {
            return String.join(READ_ALL_LINES_DELIMITER, Files.readAllLines(filePath, STANDARD_CHARSET));
        } catch (IOException e) {
            throw new InputFileReadingFailedException(filePath, e);
        }
    }
}
