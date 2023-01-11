package michael.linker.gestureid.data.event.synchronizer.model;

import java.util.List;

import michael.linker.gestureid.data.sensor.model.ASensorModel;

/**
 * A base class for a synchronized event that provides general information about the event
 */
public class SynchronizedEvent {
    private final String timestamp;
    private final List<ASensorModel<Float>> data;

    public SynchronizedEvent(String timestamp, List<ASensorModel<Float>> data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public List<ASensorModel<Float>> getData() {
        return data;
    }
}
