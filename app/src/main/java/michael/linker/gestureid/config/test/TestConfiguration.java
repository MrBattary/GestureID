package michael.linker.gestureid.config.test;

import michael.linker.gestureid.BuildConfig;

/**
 * Configuration during test
 */
public final class TestConfiguration {
    public static final class Build {
        public static String getTestResultFolderPath() {
            return BuildConfig.TEST_RESULT_FOLDER_PATH;
        }
    }
}
