package michael.linker.gestureid.config.system;

import michael.linker.gestureid.config.Configuration;
import michael.linker.gestureid.config.ConfigurationType;
import michael.linker.gestureid.config.IConfiguration;
import michael.linker.gestureid.config.bean.ConfigurationBean;
import michael.linker.gestureid.config.system.bean.SystemGateConfigurationBean;
import michael.linker.gestureid.data.system.gate.ISystemGate;
import michael.linker.gestureid.data.system.gate.SystemGate;

public final class SystemGateConfiguration implements IConfiguration {
    private static ConfigurationBean<ISystemGate> systemGateBean = null;

    public static ISystemGate getSystemGate() {
        if (systemGateBean == null) {
            systemGateBean = new SystemGateConfigurationBean(new SystemGate());
        }
        return (ISystemGate) systemGateBean;
    }

    @Override
    public void configure() {
        if (systemGateBean != null && systemGateBean.getImplementation() != null) {
            ((ISystemGate) systemGateBean).shutdown();
        }
        switch (SystemConfiguration.Type.Status.valueOf(
                Configuration.getConfiguration(ConfigurationType.SYSTEM_GATE))) {
            case ENABLED:
                systemGateBean = new SystemGateConfigurationBean(new SystemGate());
                break;
            case DISABLED:
            default:
                systemGateBean = new SystemGateConfigurationBean(null);
        }
    }
}
