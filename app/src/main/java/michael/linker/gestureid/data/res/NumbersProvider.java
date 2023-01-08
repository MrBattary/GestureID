package michael.linker.gestureid.data.res;

import android.content.res.Resources;

import michael.linker.gestureid.core.App;

public class NumbersProvider {
    private static final Resources RESOURCES = App.getRes();

    public static int getInteger(final int id) {
        return RESOURCES.getInteger(id);
    }

    public static float getFloat(final int id) {
        return RESOURCES.getFloat(id);
    }
}
