package michael.linker.gestureid.event.accumulator.model;

import java.util.List;

import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;

public class AccumulatedEpisode {
    private final List<SynchronizedEvent> eventList;

    public AccumulatedEpisode(List<SynchronizedEvent> eventList) {
        this.eventList = eventList;
    }

    public List<SynchronizedEvent> getData() {
        return eventList;
    }
}
