package michael.linker.gestureid.config.event;

import michael.linker.gestureid.event.synchronizer.EventSynchronizer;
import michael.linker.gestureid.event.synchronizer.IEventSynchronizer;

public final class EventSynchronizerConfiguration {
    private static IEventSynchronizer eventSynchronizer = null;

    public static IEventSynchronizer getEventSynchronizer() {
        if (eventSynchronizer == null) {
            eventSynchronizer = new EventSynchronizer();
        }
        return eventSynchronizer;
    }
}
