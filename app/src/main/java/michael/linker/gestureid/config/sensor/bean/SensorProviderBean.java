package michael.linker.gestureid.config.sensor.bean;

import java.util.List;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.core.sensor.sensor.SensorWrapper;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.data.sensor.provider.ISensorProvider;
import michael.linker.gestureid.data.sensor.provider.SensorProviderNotFoundException;

public class SensorProviderBean extends ConfigurationBean<ISensorProvider> implements
        ISensorProvider {
    public SensorProviderBean(ISensorProvider defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public ISensorProvider implement(ISensorProvider newImplementation) {
        setImplementation(newImplementation);
        return getImplementation();
    }

    @Override
    public SensorWrapper getSensor(SensorType sensorType) throws SensorProviderNotFoundException {
        return getImplementation().getSensor(sensorType);
    }

    @Override
    public List<SensorWrapper> getActivatedSensors() throws SensorProviderNotFoundException {
        return getImplementation().getActivatedSensors();
    }

    @Override
    public List<SensorWrapper> getSensors() {
        return getImplementation().getSensors();
    }
}
