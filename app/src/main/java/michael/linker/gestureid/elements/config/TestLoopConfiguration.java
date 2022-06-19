package michael.linker.gestureid.elements.config;

import java.io.File;

/**
 * Shared resources for the every test loop
 */
public final class TestLoopConfiguration {
    private static File destination;
    private static String testLoopFolder;

    public static File getDestination() {
        return destination;
    }

    public static void setDestination(File destination) {
        TestLoopConfiguration.destination = destination;
    }

    public static String getTestLoopFolder() {
        return testLoopFolder;
    }

    public static void setTestLoopFolder(String testLoopFolder) {
        TestLoopConfiguration.testLoopFolder = testLoopFolder;
    }
}
