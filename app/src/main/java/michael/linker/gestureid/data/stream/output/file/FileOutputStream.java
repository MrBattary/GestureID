package michael.linker.gestureid.data.stream.output.file;

import android.os.Environment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import michael.linker.gestureid.R;
import michael.linker.gestureid.data.res.StringsProvider;

public class FileOutputStream implements IFileOutputStream {
    private Path folderPath, filePath;

    /**
     * Open output stream for file in the application root folder.
     *
     * @param fileName - name of the file.
     */
    public FileOutputStream(String fileName) throws FileOutputStreamException {
        createFolderIfNeeded(null);
        createFile(fileName);
    }

    /**
     * Open output stream for file in the folder which in the application root folder.
     *
     * @param fileName   - name of the file.
     * @param folderName - folder (or folders) for the new file.
     * @throws FileOutputStreamException - If it is impossible to open output file stream.
     */
    public FileOutputStream(String fileName, String folderName) throws FileOutputStreamException {
        createFolderIfNeeded(folderName);
        createFile(fileName);
    }

    @Override
    public void writeLine(String line) throws FileOutputStreamException {
        try {
            Files.write(filePath, List.of(line), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Path getOutputFilePath() {
        return filePath;
    }

    private void createFolderIfNeeded(String folderName) throws FileOutputStreamException {
        String storageAbsolutePath =
                Environment.getExternalStorageDirectory().getAbsolutePath();
        if (folderName != null) {
            folderPath = Paths.get(storageAbsolutePath,
                    StringsProvider.getString(R.string.app_name), folderName);
        } else {
            folderPath = Paths.get(storageAbsolutePath,
                    StringsProvider.getString(R.string.app_name));
        }
        try {
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }
        } catch (IOException e) {
            throw new FileOutputStreamException(
                    "Failed to create directory " + folderPath.toString(), e);
        }
    }

    private void createFile(String fileName) {
        filePath = Paths.get(folderPath.toString(), fileName);
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new FileOutputStreamException(
                    "Failed to create file " + filePath.toString(), e);
        }
    }
}
