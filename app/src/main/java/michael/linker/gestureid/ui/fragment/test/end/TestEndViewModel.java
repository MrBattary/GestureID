package michael.linker.gestureid.ui.fragment.test.end;

import android.content.ContentResolver;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import michael.linker.gestureid.config.system.SystemPersistentNetworkConfiguration;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.network.IPersistentSystemNetwork;

public class TestEndViewModel extends ViewModel {
    private final IPersistentSystemNetwork persistentSystemNetwork;
    private final Gson gson;

    MutableLiveData<Boolean> isWritingAllowed;
    private Uri filepath;

    public TestEndViewModel() {
        persistentSystemNetwork = SystemPersistentNetworkConfiguration.getPersistentNetwork();
        gson = new Gson();
        isWritingAllowed = new MutableLiveData<>();
    }

    public LiveData<Boolean> getIsWritingAllowed() {
        return isWritingAllowed;
    }

    public void restrictWriting() {
        isWritingAllowed.postValue(false);
    }

    public void allowWriting(Uri filepath) {
        this.filepath = filepath;
        isWritingAllowed.postValue(true);
    }

    public void writeDataFromNetworkToStream(ContentResolver contentResolver) throws IOException {
        try (PrintWriter printWriter =
                     new PrintWriter(new OutputStreamWriter(
                             contentResolver.openOutputStream(filepath),
                             StandardCharsets.UTF_8))) {
            List<EpisodeMetrics> nodes = persistentSystemNetwork.getNodes();
            for (EpisodeMetrics node : nodes) {
                printWriter.write(gson.toJson(node).toCharArray());
                printWriter.println();
            }
            printWriter.flush();
        }
    }
}