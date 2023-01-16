package michael.linker.gestureid.config.sensor.bean;

import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.data.sensor.listener.manager.ISensorListenerManager;
import michael.linker.gestureid.data.sensor.listener.manager.SensorListenerManagerFailedException;

public class SensorListenerManagerBean
        extends ConfigurationBean<ISensorListenerManager>
        implements ISensorListenerManager {
    public SensorListenerManagerBean(ISensorListenerManager defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public ISensorListenerManager implement(ISensorListenerManager newImplementation) {
        setImplementation(newImplementation);
        return getImplementation();
    }

    @Override
    public Boolean isListenerRegistered(SensorType sensorType) {
        return getImplementation().isListenerRegistered(sensorType);
    }

    @Override
    public void registerListener(SensorType sensorType)
            throws SensorListenerManagerFailedException {
        getImplementation().registerListener(sensorType);
    }

    @Override
    public void unregisterListener(SensorType sensorType) {
        getImplementation().unregisterListener(sensorType);
    }

    @Override
    public void unregisterAllListeners() {
        getImplementation().unregisterAllListeners();
    }
}
