package michael.linker.gestureid.data.res;

import android.content.res.Resources;

import michael.linker.gestureid.core.App;

public class StringsProvider {
    private static final Resources RESOURCES = App.getRes();

    public static String getString(final int id) {
        return RESOURCES.getString(id);
    }
}
