package michael.linker.gestureid.config.event;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.config.event.bean.EventSynchronizerBean;
import michael.linker.gestureid.data.event.synchronizer.EventSynchronizer;
import michael.linker.gestureid.data.event.synchronizer.IEventSynchronizer;

public final class EventSynchronizerConfiguration {
    private static ConfigurationBean<IEventSynchronizer> eventSynchronizerBean = null;

    public static IEventSynchronizer getEventSynchronizer() {
        if (eventSynchronizerBean == null) {
            eventSynchronizerBean = new EventSynchronizerBean(new EventSynchronizer());
        }
        return (IEventSynchronizer) eventSynchronizerBean;
    }

    public static void configure() {
        if (eventSynchronizerBean == null) {
            eventSynchronizerBean = new EventSynchronizerBean(new EventSynchronizer());
        } else {
            eventSynchronizerBean.implement(new EventSynchronizer());
        }
    }
}
