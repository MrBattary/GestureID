package michael.linker.gestureid.analyzer.addons.file;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public interface IFile {
    Charset STANDARD_CHARSET = StandardCharsets.UTF_8;

    Path getPath();
}
