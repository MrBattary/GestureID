package michael.linker.gestureid.data.system.network;

import android.util.Log;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.config.system.SystemNetworkDatabaseConfiguration;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.db.AppDatabase;
import michael.linker.gestureid.data.system.db.network.entity.NetworkHash;
import michael.linker.gestureid.data.system.db.network.entity.NetworkNode;

public class DatabaseSystemNetwork extends LocalSystemNetwork implements IPersistentSystemNetwork {
    private static final String TAG = DatabaseSystemNetwork.class.getCanonicalName();

    private final AppDatabase db;
    private final Gson gson;

    public DatabaseSystemNetwork() {
        db = SystemNetworkDatabaseConfiguration.getDatabase();
        gson = new Gson();
        initNodes();
    }

    private void initNodes() {
        Log.i(TAG, "Database system network started nodes initialization");
        String hash = SystemConfiguration.Build.getBuildHash();
        NetworkHash dbNetworkHash = db.networkHashDao().get();
        if (dbNetworkHash == null || !hash.equals(dbNetworkHash.hashValue)) {
            Log.i(TAG, "Database system network detects configuration change");
            NetworkHash networkHash = new NetworkHash();
            networkHash.hashValue = hash;
            db.networkHashDao().set(networkHash);
            db.networkNodeDao().deleteAll();
            Log.i(TAG, "Database system network successfully refreshed the storage");
        } else {
            List<String> nodesFromDb = db.networkNodeDao().getAllNodes()
                    .stream()
                    .map(networkNode -> networkNode.nodeModel)
                    .collect(Collectors.toList());
            for (String nodeJson : nodesFromDb) {
                super.create(gson.fromJson(nodeJson, EpisodeMetrics.class));
            }
            Log.i(TAG, "Database system network successfully loaded nodes from the storage");
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
        Log.i(TAG, "Database system network finished persist to the storage");
    }
}
