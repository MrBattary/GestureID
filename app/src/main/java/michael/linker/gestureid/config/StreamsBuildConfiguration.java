package michael.linker.gestureid.config;

import michael.linker.gestureid.stream.output.type.OutputStreamType;

/**
 * Wrapper for the streams build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestureid.stream
 */
// TODO: STUBS!
public final class StreamsBuildConfiguration {
    public static OutputStreamType getOutputStreamType() {
        return null;
        //return BuildConfig.OUTPUT_STREAM_TYPE;
    }

    public static String getFileOutputDirectory() {
        return null;
        //return BuildConfig.FILE_OUTPUT_DIRECTORY;
    }

    // TODO: Add db stream
}
