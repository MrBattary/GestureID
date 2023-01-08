package michael.linker.gestureid.sensor.listener.manager;

import android.util.ArraySet;

import java.util.HashSet;
import java.util.Set;

import michael.linker.gestureid.config.sensor.SensorListenerConfiguration;
import michael.linker.gestureid.core.sensor.manager.HardwareSensorManagerSingleton;
import michael.linker.gestureid.config.sensor.SensorProviderConfiguration;
import michael.linker.gestureid.sensor.listener.ISensorListener;
import michael.linker.gestureid.sensor.listener.provider.ISensorListenerProvider;
import michael.linker.gestureid.sensor.listener.provider.SensorListenerProviderNotFoundException;
import michael.linker.gestureid.sensor.provider.ISensorProvider;
import michael.linker.gestureid.sensor.provider.SensorProviderNotFoundException;
import michael.linker.gestureid.core.sensor.sensor.type.SensorDelayType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.core.sensor.manager.AHardwareSensorManager;

public class SensorListenerManager implements ISensorListenerManager {
    private final ISensorListenerProvider sensorListenerProvider;
    private final AHardwareSensorManager sensorManager;
    private final ISensorProvider sensorProvider;

    private final Set<SensorType> registeredListeners;
    private final SensorDelayType delay;

    public SensorListenerManager() {
        this.sensorListenerProvider = SensorListenerConfiguration.getSensorListenerProvider();
        this.sensorManager = HardwareSensorManagerSingleton.getInstance();
        this.sensorProvider = SensorProviderConfiguration.getSensorProvider();

        this.registeredListeners = new ArraySet<>();
        this.delay = SensorListenerConfiguration.Build.getSensorDelay();
    }

    @Override
    public Boolean isListenerRegistered(SensorType sensorType) {
        return registeredListeners.contains(sensorType);
    }

    @Override
    public void registerListener(SensorType sensorType)
            throws SensorListenerManagerFailedException {
        if (isListenerRegistered(sensorType)) {
            throw new SensorListenerManagerFailedException("The " + sensorType.toString()
                    + " listener is already registered!");
        }
        try {
            sensorManager.registerListener(getListener(sensorType),
                    sensorProvider.getSensor(sensorType).getHardwareSensor(), delay.toInt());
            registeredListeners.add(sensorType);
        } catch (SensorListenerProviderNotFoundException | SensorProviderNotFoundException e) {
            throw new SensorListenerManagerFailedException(
                    "Unable to register the " + sensorType.toString() + " listener!", e);
        }
    }

    @Override
    public void unregisterListener(SensorType sensorType) {
        if (isListenerRegistered(sensorType)) {
            unregisterOneListener(sensorType);
        }
    }

    @Override
    public void unregisterAllListeners() {
        Set<SensorType> tempSet = new HashSet<>(registeredListeners);
        for (SensorType sensorType : tempSet) {
            unregisterOneListener(sensorType);
        }
    }

    private void unregisterOneListener(SensorType sensorType) {
        sensorManager.unregisterListener(getListener(sensorType));
        registeredListeners.remove(sensorType);
    }

    private ISensorListener getListener(SensorType sensorType)
            throws SensorListenerProviderNotFoundException {
        return sensorListenerProvider.getListener(sensorType);
    }
}
