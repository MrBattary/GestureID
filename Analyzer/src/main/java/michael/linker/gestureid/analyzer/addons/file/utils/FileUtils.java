package michael.linker.gestureid.analyzer.addons.file.utils;

import michael.linker.gestureid.analyzer.addons.file.exception.FileCreationFaultException;
import michael.linker.gestureid.analyzer.addons.file.exception.FileNotDirectoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
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
                log.info("File paths was found in the target directory " + pathToDirectory);
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

    /**
     * Creates a file in the provided directory.
     * If the directory does not exist, a directory is created.
     *
     * @param pathToDirectory Directory path.
     * @param fileName        The name of the file for which the Path object will be created.
     * @return Path object to file.
     * @throws FileCreationFaultException If the file creation failed for any reason.
     */
    public static Path createFile(String pathToDirectory, String fileName) throws FileCreationFaultException {
        Path newFilePath = Paths.get(pathToDirectory, fileName);

        try {
            Files.createDirectories(newFilePath.getParent());
            Files.createFile(newFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newFilePath;
    }

    public static void deleteFolder(String pathToDirectory) throws FileNotDirectoryException {
        Path directoryPath = Paths.get(pathToDirectory);
        try (Stream<Path> pathStream = Files.walk(directoryPath)) {
            pathStream.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new FileNotDirectoryException(directoryPath, e);
        }
    }
}
