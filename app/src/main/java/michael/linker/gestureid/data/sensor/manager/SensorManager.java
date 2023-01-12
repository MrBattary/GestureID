package michael.linker.gestureid.data.sensor.manager;

import michael.linker.gestureid.config.event.EventSynchronizerConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerManagerConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerSuppressorConfiguration;
import michael.linker.gestureid.config.sensor.SensorProviderConfiguration;
import michael.linker.gestureid.data.event.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestureid.data.event.synchronizer.IEventSynchronizer;
import michael.linker.gestureid.data.sensor.listener.manager.ISensorListenerManager;
import michael.linker.gestureid.data.sensor.listener.manager.SensorListenerManagerFailedException;
import michael.linker.gestureid.data.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.data.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestureid.data.sensor.provider.ISensorProvider;
import michael.linker.gestureid.data.sensor.provider.SensorProviderNotFoundException;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.core.sensor.sensor.SensorWrapper;
import michael.linker.gestureid.data.sensor.manager.exception.SensorManagerFailedException;

public class SensorManager implements ISensorManager {
    // Sensor
    private ISensorProvider sensorProvider;
    // Sensor listener
    private ISensorListenerSuppressor sensorListenerSuppressor;
    private ISensorListenerManager sensorListenerManager;
    // Event synchronizer
    private IEventSynchronizer eventSynchronizer;

    public SensorManager() throws SensorManagerFailedException {
        initializeDependencies();
        registerListenersForActivatedSensors();
    }

    private void initializeDependencies() {
        sensorProvider = SensorProviderConfiguration.getSensorProvider();
        sensorListenerSuppressor = SensorListenerSuppressorConfiguration.getSensorListenerSuppressor();
        sensorListenerManager = SensorListenerManagerConfiguration.getSensorListenerManager();
        eventSynchronizer = EventSynchronizerConfiguration.getEventSynchronizer();
    }

    private void registerListenersForActivatedSensors() {
        try {
            for (SensorWrapper sensorWrapper : sensorProvider.getActivatedSensors()) {
                SensorType sensorType = sensorWrapper.getSensorType();
                eventSynchronizer.attach(sensorType);
                sensorListenerManager.registerListener(sensorType);
            }
        } catch (SensorProviderNotFoundException |
                EventSynchronizerFailedException |
                SensorListenerManagerFailedException |
                SensorListenerSuppressorNotFoundException e) {
            throw new SensorManagerFailedException(
                    "Error during the initialization of the sensor manager!", e);
        }
    }

    private void unregisterRegisteredListeners() {
        eventSynchronizer.detachAll();
        sensorListenerManager.unregisterAllListeners();
    }

    @Override
    public boolean isRegisteringSuppressed() {
        return sensorListenerSuppressor.isAllListenersSuppressed();
    }

    @Override
    public void suppressRegistering() {
        sensorListenerSuppressor.suppressAllListeners();
    }

    @Override
    public void unsuppressRegistering() {
        sensorListenerSuppressor.unsuppressAllListeners();
    }

    @Override
    public void destroy() {
        suppressRegistering();
        unregisterRegisteredListeners();
    }
}
