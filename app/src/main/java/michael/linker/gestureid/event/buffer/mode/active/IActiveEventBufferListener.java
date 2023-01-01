package michael.linker.gestureid.event.buffer.mode.active;

import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

/**
 * Listener of active buffer updates.
 */
public interface IActiveEventBufferListener {
    /**
     * Calls by IActiveEventBuffer impl when buffer updates with the sensor event.
     *
     * @param eventSingleModel last buffer sensor event update.
     */
    void notifyAboutEvent(SynchronizedEvent eventSingleModel);
}
