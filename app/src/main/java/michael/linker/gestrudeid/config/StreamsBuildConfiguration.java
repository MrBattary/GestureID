package michael.linker.gestrudeid.config;

import michael.linker.gestrudeid.BuildConfig;
import michael.linker.gestrudeid.output.types.SensorStreamType;

/**
 * Wrapper for the streams build variables
 * "build.gradle" file in the application main folder
 */
public final class StreamsBuildConfiguration {
    public static SensorStreamType getMainOutputStreamType() {
        return BuildConfig.MAIN_OUTPUT_STREAM_TYPE;
    }

    public static SensorStreamType getBackupOutputStreamType() {
        return BuildConfig.BACKUP_OUTPUT_STREAM_TYPE;
    }

    // TODO: Add file stream

    // TODO: Add db stream
}
