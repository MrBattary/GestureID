package michael.linker.gestureid.config.sensor.bean;

import java.util.Map;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.data.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.data.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;

public class SensorListenerSuppressorBean
        extends ConfigurationBean<ISensorListenerSuppressor>
        implements ISensorListenerSuppressor {
    public SensorListenerSuppressorBean(ISensorListenerSuppressor defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public ISensorListenerSuppressor implement(ISensorListenerSuppressor newImplementation) {
        setImplementation(newImplementation);
        return getImplementation();
    }

    @Override
    public void suppressAllListeners() {
        getImplementation().suppressAllListeners();
    }

    @Override
    public void unsuppressAllListeners() {
        getImplementation().unsuppressAllListeners();
    }

    @Override
    public boolean isAllListenersSuppressed() {
        return getImplementation().isAllListenersSuppressed();
    }

    @Override
    public Boolean isListenerSuppressed(SensorType sensorType)
            throws SensorListenerSuppressorNotFoundException {
        return getImplementation().isListenerSuppressed(sensorType);
    }

    @Override
    public void suppressListener(SensorType sensorType)
            throws SensorListenerSuppressorNotFoundException {
        getImplementation().suppressListener(sensorType);
    }

    @Override
    public void unsuppressListener(SensorType sensorType)
            throws SensorListenerSuppressorNotFoundException {
        getImplementation().unsuppressListener(sensorType);
    }

    @Override
    public Map<SensorType, Boolean> getListenersSuppressedStatus() {
        return getImplementation().getListenersSuppressedStatus();
    }

    @Override
    public void registerListener(SensorType sensorType) {
        getImplementation().registerListener(sensorType);
    }
}
