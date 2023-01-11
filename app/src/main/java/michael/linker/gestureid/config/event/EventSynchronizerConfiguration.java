package michael.linker.gestureid.config.event;

import michael.linker.gestureid.data.event.synchronizer.EventSynchronizer;
import michael.linker.gestureid.data.event.synchronizer.IEventSynchronizer;

public final class EventSynchronizerConfiguration {
    private static IEventSynchronizer eventSynchronizer = null;

    public static IEventSynchronizer getEventSynchronizer() {
        if (eventSynchronizer == null) {
            eventSynchronizer = new EventSynchronizer();
        }
        return eventSynchronizer;
    }
}
