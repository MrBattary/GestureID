package michael.linker.gestureid.config.sensor.bean;

import michael.linker.gestureid.config.ConfigurationBean;
import michael.linker.gestureid.data.sensor.manager.ISensorManager;

public class SensorManagerBean extends ConfigurationBean<ISensorManager> implements ISensorManager {
    public SensorManagerBean(ISensorManager defaultImplementation) {
        super(defaultImplementation);
    }

    @Override
    public ISensorManager implement(ISensorManager newImplementation) {
        setImplementation(newImplementation);
        return getImplementation();
    }

    @Override
    public boolean isRegisteringSuppressed() {
        return getImplementation().isRegisteringSuppressed();
    }

    @Override
    public void suppressRegistering() {
        getImplementation().suppressRegistering();
    }

    @Override
    public void unsuppressRegistering() {
        getImplementation().unsuppressRegistering();
    }

    @Override
    public void destroy() {
        getImplementation().destroy();
    }
}
