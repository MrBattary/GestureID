package michael.linker.gestrudeid.config;

import michael.linker.gestrudeid.BuildConfig;
import michael.linker.gestrudeid.stream.output.type.OutputStreamType;

/**
 * Wrapper for the streams build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestrudeid.stream
 */
public final class StreamsBuildConfiguration {
    public static OutputStreamType getOutputStreamType() {
        return BuildConfig.OUTPUT_STREAM_TYPE;
    }

    public static String getFileOutputDirectory() {
        return BuildConfig.FILE_OUTPUT_DIRECTORY;
    }

    // TODO: Add db stream
}
