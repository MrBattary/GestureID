package michael.linker.gestureid.data.system.db.network.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "network_hash")
public class NetworkHash {
    @PrimaryKey
    @ColumnInfo(name = "hash_value")
    public String hashValue;
}
