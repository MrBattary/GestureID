package michael.linker.gestrudeid.config;

import michael.linker.gestrudeid.BuildConfig;
import michael.linker.gestrudeid.streams.output.types.OutputStreamType;

/**
 * Wrapper for the streams build variables
 * "build.gradle" file in the application main folder
 */
public final class StreamsBuildConfiguration {
    public static OutputStreamType getMainOutputStreamType() {
        return BuildConfig.MAIN_OUTPUT_STREAM_TYPE;
    }

    public static OutputStreamType getBackupOutputStreamType() {
        return BuildConfig.BACKUP_OUTPUT_STREAM_TYPE;
    }

    // TODO: Add file stream

    // TODO: Add db stream
}
