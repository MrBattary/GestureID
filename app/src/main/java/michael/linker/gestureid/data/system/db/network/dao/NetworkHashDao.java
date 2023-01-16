package michael.linker.gestureid.data.system.db.network.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import michael.linker.gestureid.data.system.db.network.entity.NetworkHash;

@Dao
public interface NetworkHashDao {
    @Transaction
    default void set(NetworkHash networkHash) {
        deleteAll();
        insert(networkHash);
    }

    @Query("SELECT * FROM network_hash LIMIT 1")
    NetworkHash get();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NetworkHash networkHash);

    @Query("DELETE FROM network_hash")
    void deleteAll();
}
