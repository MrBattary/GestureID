package michael.linker.gestureid.data.system.db.network.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "network_nodes")
public class NetworkNode {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "node_id")
    public long nodeId;

    @ColumnInfo(name = "node_model")
    public String nodeModel;

    public NetworkNode() {
    }

    @Ignore
    public NetworkNode(String nodeModel) {
        this.nodeModel = nodeModel;
    }
}
