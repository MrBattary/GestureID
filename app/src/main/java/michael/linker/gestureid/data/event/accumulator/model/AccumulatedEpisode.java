package michael.linker.gestureid.data.event.accumulator.model;

import java.util.List;

import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;

public class AccumulatedEpisode {
    private final List<SynchronizedEvent> eventList;

    public AccumulatedEpisode(List<SynchronizedEvent> eventList) {
        this.eventList = eventList;
    }

    public AccumulatedEpisode(AccumulatedEpisode accumulatedEpisode) {
        eventList = accumulatedEpisode.getData();
    }

    public List<SynchronizedEvent> getData() {
        return eventList;
    }
}
