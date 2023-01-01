package michael.linker.gestureid.world;

import michael.linker.gestureid.event.buffer.mode.IEventBuffer;
import michael.linker.gestureid.sensor.listener.manager.ISensorListenerManager;
import michael.linker.gestureid.sensor.listener.manager.SensorListenerManager;
import michael.linker.gestureid.sensor.listener.manager.SensorListenerManagerFailedException;
import michael.linker.gestureid.sensor.listener.provider.ISensorListenerProvider;
import michael.linker.gestureid.sensor.listener.provider.SensorListenerProvider;
import michael.linker.gestureid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestureid.sensor.listener.suppressor.SensorListenerSuppressor;
import michael.linker.gestureid.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestureid.sensor.provider.ISensorProvider;
import michael.linker.gestureid.sensor.provider.SensorProvider;
import michael.linker.gestureid.sensor.provider.SensorProviderNotFoundException;
import michael.linker.gestureid.sensor.type.SensorType;
import michael.linker.gestureid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestureid.sensor.wrapper.sensor.SensorWrapper;
import michael.linker.gestureid.event.synchronizer.EventSynchronizer;
import michael.linker.gestureid.event.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestureid.event.synchronizer.IEventSynchronizer;
import michael.linker.gestureid.world.exception.WorldFailedException;

public class World implements IWorld {
    // Sensor
    private final ASensorManager sensorManager;
    private ISensorProvider sensorProvider;
    // Sensor listener
    private ISensorListenerSuppressor sensorListenerSuppressor;
    private ISensorListenerProvider sensorListenerProvider;
    private ISensorListenerManager sensorListenerManager;
    // Event synchronizer
    private IEventSynchronizer eventSynchronizer;
    // Event buffer
    private IEventBuffer eventBuffer;

    public World(final ASensorManager sensorManager) throws WorldFailedException {
        this.sensorManager = sensorManager;
        initializeRequiredParts();
        registerListenersForActivatedSensors();
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
        unregisterRegisteredEntities();
    }

    private void initializeRequiredParts() {
        initializeEventBufferPart();
        initializeEventSynchronizerPart();
        initializeSensorPart();
        initializeListenerPart();
    }

    private void initializeEventBufferPart() {
        // TODO: init eventBuffer
    }

    private void initializeEventSynchronizerPart() {
        eventSynchronizer = new EventSynchronizer(eventBuffer);
    }

    private void initializeSensorPart() {
        sensorProvider = new SensorProvider(sensorManager);
    }

    private void initializeListenerPart() {
        sensorListenerSuppressor = new SensorListenerSuppressor();
        sensorListenerProvider =
                new SensorListenerProvider(eventSynchronizer, sensorListenerSuppressor);
        sensorListenerManager =
                new SensorListenerManager(sensorListenerProvider, sensorManager, sensorProvider);
    }

    private void registerListenersForActivatedSensors() {
        try {
            for (SensorWrapper sensorWrapper : sensorProvider.getActivatedSensors()) {
                SensorType sensorType = sensorWrapper.getSensorType();
                eventSynchronizer.attachOneListener(sensorType);
                sensorListenerManager.registerListener(sensorType);
            }
        } catch (SensorProviderNotFoundException |
                EventSynchronizerFailedException |
                SensorListenerManagerFailedException |
                SensorListenerSuppressorNotFoundException e) {
            throw new WorldFailedException(
                    "Error during the initialization of the sensor world!", e);
        }
    }

    private void unregisterRegisteredEntities() {
        sensorListenerManager.unregisterAllListeners();
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
        super.finalize();
    }
}
