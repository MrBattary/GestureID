package michael.linker.gestureid.data.system.network;

import java.util.List;

import michael.linker.gestureid.data.system.calculator.model.EpisodeMetrics;
import michael.linker.gestureid.data.system.network.type.SystemNetworkResult;

public interface ISystemNetwork {
    /**
     * Proceed metrics to the node network.
     * Every node tries to recognize the metric, if recognition was successful, then updates the
     * node which recognized metric and returns positive result.
     * If recognition failed, a negative result is returned.
     *
     * @param metrics calculated metrics.
     * @return result as enum.
     */
    SystemNetworkResult proceed(EpisodeMetrics metrics);

    /**
     * Creates a new node in the node network with the provided metrics.
     *
     * @param metrics calculated metrics.
     */
    void create(EpisodeMetrics metrics);

    /**
     * Returns internal node network.
     *
     * @return node network.
     */
    List<EpisodeMetrics> getNodes();

    /**
     * Removes all nodes.
     */
    void purgeNodes();
}
