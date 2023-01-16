package michael.linker.gestureid.data.event.synchronizer.model;

import java.util.List;

import michael.linker.gestureid.data.sensor.model.ASensorModel;

/**
 * A base class for a synchronized event that provides general information about the event
 */
public class SynchronizedEvent {
    private final Double timestamp;
    private final List<ASensorModel<Double>> data;

    public SynchronizedEvent(Double timestamp, List<ASensorModel<Double>> data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public List<ASensorModel<Double>> getData() {
        return data;
    }
}
