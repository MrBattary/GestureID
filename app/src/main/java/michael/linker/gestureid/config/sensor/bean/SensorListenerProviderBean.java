package michael.linker.gestureid.config.sensor.bean;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.data.sensor.listener.ISensorListener;
import michael.linker.gestureid.data.sensor.listener.provider.ISensorListenerProvider;
import michael.linker.gestureid.data.sensor.listener.provider.SensorListenerProviderNotFoundException;

public class SensorListenerProviderBean
        extends ConfigurationBean<ISensorListenerProvider>
        implements ISensorListenerProvider {
    public SensorListenerProviderBean(ISensorListenerProvider defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public ISensorListenerProvider implement(ISensorListenerProvider newImplementation) {
        setImplementation(newImplementation);
        return getImplementation();
    }

    @Override
    public ISensorListener getListener(SensorType sensorType)
            throws SensorListenerProviderNotFoundException {
        return getImplementation().getListener(sensorType);
    }
}
