package michael.linker.gestrudeid.config;

import michael.linker.gestrudeid.BuildConfig;
import michael.linker.gestrudeid.sensor.output.SensorStreamType;

/**
 * Wrapper for the streams build variables
 * "build.gradle" file in the application main folder
 */
public final class StreamsBuildConfiguration {
    SensorStreamType getStream() {
        return BuildConfig.OUTPUT_STREAM;
    }

    SensorStreamType getDefaultStream() {
        return BuildConfig.OUTPUT_STREAM_DEFAULT;
    }

    // TODO: Add file stream

    // TODO: Add db stream
}
