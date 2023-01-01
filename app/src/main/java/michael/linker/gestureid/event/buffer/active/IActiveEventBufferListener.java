package michael.linker.gestureid.event.buffer.active;

import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventSingleModel;

/**
 * Listener of active buffer updates.
 */
public interface IActiveEventBufferListener {
    /**
     * Calls by IActiveEventBuffer impl when buffer updates.
     *
     * @param eventSingleModel last buffer update.
     */
    void notifyAboutEvent(SynchronizedEventSingleModel eventSingleModel);
}
