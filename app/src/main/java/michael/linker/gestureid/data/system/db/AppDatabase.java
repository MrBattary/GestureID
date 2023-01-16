package michael.linker.gestureid.data.system.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import michael.linker.gestureid.data.system.db.network.dao.NetworkHashDao;
import michael.linker.gestureid.data.system.db.network.dao.NetworkNodeDao;
import michael.linker.gestureid.data.system.db.network.entity.NetworkHash;
import michael.linker.gestureid.data.system.db.network.entity.NetworkNode;

@Database(
        entities = {
                NetworkHash.class,
                NetworkNode.class},
        version = 1,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NetworkHashDao networkHashDao();

    public abstract NetworkNodeDao networkNodeDao();
}
