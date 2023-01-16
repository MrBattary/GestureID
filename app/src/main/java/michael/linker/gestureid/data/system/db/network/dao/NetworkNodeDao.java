package michael.linker.gestureid.data.system.db.network.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import michael.linker.gestureid.data.system.db.network.entity.NetworkNode;

@Dao
public interface NetworkNodeDao {
    @Transaction
    default void setNewNodes(List<NetworkNode> nodes) {
        deleteAll();
        insertAll(nodes);
    }

    @Query("SELECT * FROM network_nodes")
    List<NetworkNode> getAllNodes();

    @Insert
    void insertAll(List<NetworkNode> nodes);

    @Query("DELETE FROM network_nodes")
    void deleteAll();
}
