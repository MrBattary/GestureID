package michael.linker.gestureid.data.system.processor;

import android.util.Log;

import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.calculator.ISystemCalculator;
import michael.linker.gestureid.data.system.calculator.SystemCalculator;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.network.DatabaseSystemNetwork;
import michael.linker.gestureid.data.system.network.IPersistentSystemNetwork;
import michael.linker.gestureid.data.system.network.ISystemNetwork;
import michael.linker.gestureid.data.system.network.LocalSystemNetwork;
import michael.linker.gestureid.data.system.network.type.SystemNetworkResult;
import michael.linker.gestureid.data.system.processor.type.SystemProcessorResult;

public class SystemProcessor implements ISystemProcessor {
    private static final String TAG = SystemProcessor.class.getCanonicalName();

    private final ISystemCalculator systemCalculator;
    private final IPersistentSystemNetwork userModelNetwork;
    private final ISystemNetwork stashNetwork;
    private int unrecognizedEpisodesCounter;

    public SystemProcessor() {
        systemCalculator = new SystemCalculator();
        userModelNetwork = new DatabaseSystemNetwork();
        stashNetwork = new LocalSystemNetwork();
        unrecognizedEpisodesCounter = 0;
    }

    @Override
    public SystemProcessorResult proceed(AccumulatedEpisode accumulatedEpisode) {
        if (unrecognizedEpisodesCounter
                >= SystemConfiguration.Build.Network.getNumberOfUnrecognizedEpisodes()) {
            return SystemProcessorResult.AUTH_REQUIRED;
        }

        EpisodeMetrics metrics = systemCalculator.calculateEpisodeMetrics(accumulatedEpisode);
        Log.i(TAG, "The system processor calculated episode metrics.");
        SystemNetworkResult stashNetworkResult = stashNetwork.proceed(metrics);
        if (stashNetworkResult == SystemNetworkResult.RECOGNIZED) {
            Log.i(TAG, "The system processor recognized episode in the stash network.");
            unrecognizedEpisodesCounter++;
            if (unrecognizedEpisodesCounter
                    >= SystemConfiguration.Build.Network.getNumberOfUnrecognizedEpisodes()) {
                return SystemProcessorResult.AUTH_REQUIRED;
            }
        } else {
            SystemNetworkResult userNetworkResult = userModelNetwork.proceed(metrics);
            if (userNetworkResult == SystemNetworkResult.RECOGNIZED) {
                Log.i(TAG, "The system processor recognized episode in the user model network.");
                flushUnrecognizedEpisodes();
                unrecognizedEpisodesCounter = 0;
            } else {
                Log.i(TAG, "The system processor did not recognize the episode "
                        + "in the user network model and created a node in the stash network.");
                stashNetwork.create(metrics);
                unrecognizedEpisodesCounter++;
                if (unrecognizedEpisodesCounter
                        >= SystemConfiguration.Build.Network.getNumberOfUnrecognizedEpisodes()) {
                    return SystemProcessorResult.AUTH_REQUIRED;
                }
            }
        }
        return SystemProcessorResult.ACCEPTED;
    }

    @Override
    public void authAcquired() {
        Log.i(TAG, "The system processor has received authorization.");
        flushUnrecognizedEpisodes();
        unrecognizedEpisodesCounter = 0;
    }

    private void flushUnrecognizedEpisodes() {
        for (EpisodeMetrics unrecognizedMetrics : stashNetwork.getNodes()) {
            userModelNetwork.create(unrecognizedMetrics);
        }
        stashNetwork.purgeNodes();
    }

    @Override
    public void saveData() {
        Log.i(TAG, "System processor has started saving network nodes.");
        userModelNetwork.persist();
        Log.i(TAG, "System processor has finished saving network nodes.");
    }
}
