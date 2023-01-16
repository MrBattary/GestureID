package michael.linker.gestureid.data.system.network;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.config.system.SystemDatabaseConfiguration;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.db.AppDatabase;
import michael.linker.gestureid.data.system.db.network.entity.NetworkHash;
import michael.linker.gestureid.data.system.db.network.entity.NetworkNode;

public class DatabaseSystemNetwork extends LocalSystemNetwork implements IPersistentSystemNetwork {
    private final AppDatabase db;
    private final Gson gson;

    public DatabaseSystemNetwork() {
        db = SystemDatabaseConfiguration.getDatabase();
        gson = new Gson();
        initNodes();
    }

    private void initNodes() {
        String hash = SystemConfiguration.Build.getBuildHash();
        NetworkHash dbNetworkHash = db.networkHashDao().get();
        if (dbNetworkHash == null || !hash.equals(dbNetworkHash.hashValue)) {
            NetworkHash networkHash = new NetworkHash();
            networkHash.hashValue = hash;
            db.networkHashDao().set(networkHash);
        } else {
            List<String> nodesFromDb = db.networkNodeDao().getAllNodes()
                    .stream()
                    .map(networkNode -> networkNode.nodeModel)
                    .collect(Collectors.toList());
            for (String nodeJson : nodesFromDb) {
                super.create(gson.fromJson(nodeJson, EpisodeMetrics.class));
            }
        }
    }

    @Override
    public void persist() {
        List<EpisodeMetrics> localNodes = super.getNodes();
        List<NetworkNode> dbNodes = new LinkedList<>();
        for (EpisodeMetrics localNode : localNodes) {
            dbNodes.add(new NetworkNode(gson.toJson(localNode)));
        }
        db.networkNodeDao().setNewNodes(dbNodes);
    }
}
