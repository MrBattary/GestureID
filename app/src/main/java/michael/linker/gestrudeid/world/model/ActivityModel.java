package michael.linker.gestrudeid.world.model;

import java.io.File;

/**
 * A model for the data the World needs from the Activity
 */
public class ActivityModel {
    private final File appRootDirectory;

    public ActivityModel(File appRootDirectory) {
        this.appRootDirectory = appRootDirectory;
    }

    public File getAppRootDirectory() {
        return appRootDirectory;
    }
}
