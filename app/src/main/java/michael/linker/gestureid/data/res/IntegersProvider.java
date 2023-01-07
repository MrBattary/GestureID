package michael.linker.gestureid.data.res;

import android.content.res.Resources;

import michael.linker.gestureid.core.App;

public class IntegersProvider {
    private static final Resources RESOURCES = App.getRes();

    public static Integer getInteger(final int id) {
        return RESOURCES.getInteger(id);
    }
}
