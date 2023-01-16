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
        if (hash.equals(db.networkHashDao().get().hashValue)) {
            List<String> nodesFromDb = db.networkNodeDao().getAllNodes()
                    .stream()
                    .map(networkNode -> networkNode.nodeModel)
                    .collect(Collectors.toList());
            for (String nodeJson : nodesFromDb) {
                super.create(gson.fromJson(nodeJson, EpisodeMetrics.class));
            }
        } else {
            NetworkHash networkHash = new NetworkHash();
            networkHash.hashValue = hash;
            db.networkHashDao().set(networkHash);
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
