package michael.linker.gestureid.analyzer.file.utils;

import michael.linker.gestureid.analyzer.file.exception.FileNotDirectoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {
    private static final Logger log = LogManager.getLogger(FileUtils.class);

    /**
     * Get Path list for provided directory.
     *
     * @param pathToDirectory Full path to the directory.
     * @return List with Path objects (can be empty).
     * @throws FileNotDirectoryException If the object located on directoryPath is not a directory!
     */
    public static List<Path> getPathsFromDirectory(String pathToDirectory) throws FileNotDirectoryException {
        Path directoryPath = Paths.get(pathToDirectory);
        if (Files.isDirectory(directoryPath)) {
            try (Stream<Path> paths = Files.walk(directoryPath)) {
                return paths.filter(Files::isRegularFile)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                log.error(String.format("No paths were found in the directory '%s!'", pathToDirectory));
                return new ArrayList<>();
            }
        } else {
            throw new FileNotDirectoryException(directoryPath);
        }
    }
}
