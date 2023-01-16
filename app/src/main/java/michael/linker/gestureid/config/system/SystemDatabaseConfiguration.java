package michael.linker.gestureid.config.system;

import android.content.Context;

import androidx.room.Room;

import michael.linker.gestureid.core.App;
import michael.linker.gestureid.data.system.db.AppDatabase;


public class SystemDatabaseConfiguration {
    private static final String DATABASE_NAME = "user-model-network";
    private static AppDatabase sDatabase;

    public static AppDatabase getDatabase() {
        final Context context = App.getInstance().getApplicationContext();
        if (sDatabase == null) {
            sDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    DATABASE_NAME).allowMainThreadQueries().build();
        }
        return sDatabase;
    }

    public static boolean isDatabaseOpened() {
        if (sDatabase != null) {
            return sDatabase.isOpen();
        } else {
            return false;
        }
    }
}
