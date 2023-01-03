package michael.linker.gestureid.sensor.manager;

import michael.linker.gestureid.config.event.EventSynchronizerConfiguration;
import michael.linker.gestureid.config.sensor.SensorListenerConfiguration;
import michael.linker.gestureid.config.sensor.SensorProviderConfiguration;
import michael.linker.gestureid.event.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestureid.event.synchronizer.IEventSynchronizer;
import michael.linker.gestureid.sensor.listener.manager.ISensorListenerManager;
import michael.linker.gestureid.sensor.listener.manager.SensorListenerManagerFailedException;
import michael.linker.gestureid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestureid.sensor.provider.ISensorProvider;
import michael.linker.gestureid.sensor.provider.SensorProviderNotFoundException;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.core.sensor.sensor.SensorWrapper;
import michael.linker.gestureid.sensor.manager.exception.SensorManagerFailedException;

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
        sensorListenerSuppressor = SensorListenerConfiguration.getSensorListenerSuppressor();
        sensorListenerManager = SensorListenerConfiguration.getSensorListenerManager();
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

    @Override
    protected void finalize() throws Throwable {
        destroy();
        super.finalize();
    }
}
