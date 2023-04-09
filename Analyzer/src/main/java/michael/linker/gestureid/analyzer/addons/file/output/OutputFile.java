package michael.linker.gestureid.analyzer.addons.file.output;

import michael.linker.gestureid.analyzer.addons.file.output.exception.OutputFileWritingFailedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class OutputFile implements IOutputFile {
    private static final String NEW_LINE = "\n";
    private final Path filePath;

    public OutputFile(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeLine(String line) throws OutputFileWritingFailedException {
        try {
            Files.writeString(filePath, line + NEW_LINE, STANDARD_CHARSET, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new OutputFileWritingFailedException(filePath, e);
        }
    }

    @Override
    public Path getPath() {
        return filePath;
    }
}
