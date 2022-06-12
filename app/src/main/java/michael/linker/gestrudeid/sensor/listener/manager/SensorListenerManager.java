package michael.linker.gestrudeid.sensor.listener.manager;

import android.util.ArraySet;

import java.util.HashSet;
import java.util.Set;

import michael.linker.gestrudeid.config.ListenersBuildConfiguration;
import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.sensor.listener.provider.ISensorListenerProvider;
import michael.linker.gestrudeid.sensor.listener.provider.SensorListenerProviderNotFoundException;
import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.provider.ISensorProvider;
import michael.linker.gestrudeid.sensor.provider.SensorProviderNotFoundException;
import michael.linker.gestrudeid.sensor.type.SensorDelayType;
import michael.linker.gestrudeid.sensor.type.SensorType;

public class SensorListenerManager implements ISensorListenerManager {
    private final Set<SensorType> registeredListeners = new ArraySet<>();
    private final ISensorListenerProvider sensorListenerProvider;
    private final ASensorManager sensorManager;
    private final ISensorProvider sensorProvider;
    private final SensorDelayType delay;

    public SensorListenerManager(
            final ISensorListenerProvider sensorListenerProvider,
            final ASensorManager sensorManager,
            final ISensorProvider sensorProvider) {
        this.sensorListenerProvider = sensorListenerProvider;
        this.sensorManager = sensorManager;
        this.sensorProvider = sensorProvider;
        this.delay = ListenersBuildConfiguration.getSensorDelay();
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
