package michael.linker.gestureid.event.synchronizer.model;

/**
 * A base class for a synchronized event that provides general information about the event
 */
public class SynchronizedEvent {
    private final String timestamp;

    protected SynchronizedEvent(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
