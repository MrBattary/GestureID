package michael.linker.gestureid.data.system.processor;

import michael.linker.gestureid.config.system.SystemConfiguration;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.system.calculator.ISystemCalculator;
import michael.linker.gestureid.data.system.calculator.SystemCalculator;
import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.network.DatabaseSystemNetwork;
import michael.linker.gestureid.data.system.network.ISystemNetwork;
import michael.linker.gestureid.data.system.network.LocalSystemNetwork;
import michael.linker.gestureid.data.system.network.type.SystemNetworkResult;
import michael.linker.gestureid.data.system.processor.type.SystemProcessorResult;

public class SystemProcessor implements ISystemProcessor {
    private final ISystemCalculator systemCalculator;
    private final ISystemNetwork userModelNetwork, stashNetwork;
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
        SystemNetworkResult stashNetworkResult = stashNetwork.proceed(metrics);
        if (stashNetworkResult == SystemNetworkResult.RECOGNIZED) {
            unrecognizedEpisodesCounter++;
            if (unrecognizedEpisodesCounter
                    >= SystemConfiguration.Build.Network.getNumberOfUnrecognizedEpisodes()) {
                return SystemProcessorResult.AUTH_REQUIRED;
            }
        } else {
            SystemNetworkResult userNetworkResult = userModelNetwork.proceed(metrics);
            if (userNetworkResult == SystemNetworkResult.RECOGNIZED) {
                unrecognizedEpisodesCounter = 0;
            } else {
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
        for (EpisodeMetrics unrecognizedMetrics : stashNetwork.getNodes()) {
            userModelNetwork.create(unrecognizedMetrics);
        }
        stashNetwork.purgeNodes();
        unrecognizedEpisodesCounter = 0;
    }
}
