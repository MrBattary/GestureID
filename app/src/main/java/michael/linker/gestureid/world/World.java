package michael.linker.gestureid.world;

import michael.linker.gestureid.formatter.IFormatter;
import michael.linker.gestureid.formatter.factory.FormatterFactory;
import michael.linker.gestureid.formatter.factory.IFormatterFactory;
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
import michael.linker.gestureid.stream.manager.IStreamManager;
import michael.linker.gestureid.stream.manager.StreamManager;
import michael.linker.gestureid.stream.manager.StreamManagerFailedException;
import michael.linker.gestureid.stream.manager.StreamManagerNotFoundException;
import michael.linker.gestureid.stream.output.model.AOutputStreamModel;
import michael.linker.gestureid.stream.output.stream.IOutputStream;
import michael.linker.gestureid.synchronizer.EventSynchronizer;
import michael.linker.gestureid.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestureid.synchronizer.IEventSynchronizer;
import michael.linker.gestureid.world.exception.WorldFailedException;

public class World implements IWorld {
    // Sensor
    private final ASensorManager sensorManager;
    private ISensorProvider sensorProvider;
    // Streams
    private IStreamManager streamManager;
    private IOutputStream outputStream;
    // Formatter
    private IFormatterFactory formatterFactory;
    private IFormatter formatter;
    // Synchronizer
    private IEventSynchronizer eventSynchronizer;
    // Listener
    private ISensorListenerSuppressor sensorListenerSuppressor;
    private ISensorListenerProvider sensorListenerProvider;
    private ISensorListenerManager sensorListenerManager;

    public World(
            final ASensorManager sensorManager
    ) throws WorldFailedException {
        this.sensorManager = sensorManager;
        initializeRequiredParts();
        registerListenersForActivatedSensor();
    }

    @Override
    public void setNewOutputStream(AOutputStreamModel outputStreamModel) {
        try {
            closeOutputStream();
            outputStream = streamManager.getOutputStream(outputStreamModel);
            formatter.setNewOutputStream(outputStream);
        } catch (StreamManagerNotFoundException | StreamManagerFailedException e) {
            throw new WorldFailedException("It is not possible to create a new output stream!", e);
        }
    }

    @Override
    public void closeOutputStream() {
        if (outputStream != null) {
            outputStream.close();
        }
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
        closeOpenedStreams();
        unregisterRegisteredEntities();
    }

    private void initializeRequiredParts() {
        initializeSensorPart();
        initializeStreamPart();
        initializeFormatterPart();
        initializeSynchronizerPart();
        initializeListenerPart();
    }

    private void initializeSensorPart() {
        sensorProvider = new SensorProvider(sensorManager);
    }

    private void initializeStreamPart() {
        streamManager = new StreamManager();
    }

    private void initializeFormatterPart() {
        formatterFactory = new FormatterFactory();
        formatter = formatterFactory.getFormatter();
    }

    private void initializeSynchronizerPart() {
        eventSynchronizer = new EventSynchronizer(formatter);
    }

    private void initializeListenerPart() {
        sensorListenerSuppressor = new SensorListenerSuppressor();
        sensorListenerProvider =
                new SensorListenerProvider(eventSynchronizer, sensorListenerSuppressor);
        sensorListenerManager =
                new SensorListenerManager(sensorListenerProvider, sensorManager, sensorProvider);
    }

    private void registerListenersForActivatedSensor() {
        try {
            for (SensorWrapper sensorWrapper : sensorProvider.getActivatedSensors()) {
                SensorType sensorType = sensorWrapper.getSensorType();
                eventSynchronizer.attachOneListener(sensorType);
                sensorListenerManager.registerListener(sensorType);
                sensorListenerSuppressor.unsuppressListener(sensorType);
            }
        } catch (SensorProviderNotFoundException |
                EventSynchronizerFailedException |
                SensorListenerManagerFailedException |
                SensorListenerSuppressorNotFoundException e) {
            throw new
                    WorldFailedException("Error during the initialization of the sensor world!", e);
        }
    }

    private void closeOpenedStreams() {
        closeOutputStream();
        // TODO: Close input stream
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
