package michael.linker.gestureid.event.buffer.mode.active;

import java.util.List;

import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * Listener of active buffer updates.
 */
public interface IActiveEventBufferListener {
    /**
     * Calls by IActiveEventBuffer impl when buffer updates.
     *
     * @param synchronizedEventList event list.
     */
    void notifyAboutEvents(List<SynchronizedEvent> synchronizedEventList);
}
