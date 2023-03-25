package michael.linker.gestureid.ui.fragment.test.end;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import michael.linker.gestureid.config.system.SystemPersistentNetworkConfiguration;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.network.IPersistentSystemNetwork;

public class TestEndViewModel extends ViewModel {
    private final IPersistentSystemNetwork persistentSystemNetwork;
    private final Gson gson;

    public TestEndViewModel() {
        persistentSystemNetwork = SystemPersistentNetworkConfiguration.getPersistentNetwork();
        gson = new Gson();
    }


    public void writeDataFromNetworkToStream(OutputStreamWriter outputStream) throws IOException {
        List<EpisodeMetrics> nodes = persistentSystemNetwork.getNodes();

        for (EpisodeMetrics node : nodes) {
            outputStream.write(gson.toJson(node).toCharArray());
            outputStream.flush();
        }
    }
}