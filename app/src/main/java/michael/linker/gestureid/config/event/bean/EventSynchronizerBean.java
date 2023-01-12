package michael.linker.gestureid.config.event.bean;

import java.util.List;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.data.event.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestureid.data.event.synchronizer.EventSynchronizerNotFoundException;
import michael.linker.gestureid.data.event.synchronizer.IEventSynchronizer;
import michael.linker.gestureid.data.sensor.model.ASensorModel;

public class EventSynchronizerBean
        extends ConfigurationBean<IEventSynchronizer>
        implements IEventSynchronizer {
    public EventSynchronizerBean(IEventSynchronizer defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public IEventSynchronizer implement(IEventSynchronizer newImplementation) {
        setImplementation(newImplementation);
        return getImplementation();
    }

    @Override
    public void registerEvent(ASensorModel<Float> sensorModel)
            throws EventSynchronizerNotFoundException, EventSynchronizerFailedException {
        getImplementation().registerEvent(sensorModel);
    }

    @Override
    public void attach(SensorType sensorType) throws EventSynchronizerFailedException {
        getImplementation().attach(sensorType);
    }

    @Override
    public void detach(SensorType sensorType) {
        getImplementation().detach(sensorType);
    }

    @Override
    public void attach(List<SensorType> sensorTypesList) throws EventSynchronizerFailedException {
        getImplementation().attach(sensorTypesList);
    }

    @Override
    public void detach(List<SensorType> sensorTypesList) {
        getImplementation().detach(sensorTypesList);
    }

    @Override
    public void detachAll() {
        getImplementation().detachAll();
    }
}
