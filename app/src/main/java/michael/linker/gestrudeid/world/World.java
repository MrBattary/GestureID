package michael.linker.gestrudeid.world;

import michael.linker.gestrudeid.formatter.IFormatter;
import michael.linker.gestrudeid.formatter.factory.FormatterFactory;
import michael.linker.gestrudeid.formatter.factory.IFormatterFactory;
import michael.linker.gestrudeid.sensor.listener.manager.ISensorListenerManager;
import michael.linker.gestrudeid.sensor.listener.manager.SensorListenerManager;
import michael.linker.gestrudeid.sensor.listener.manager.SensorListenerManagerFailedException;
import michael.linker.gestrudeid.sensor.listener.provider.ISensorListenerProvider;
import michael.linker.gestrudeid.sensor.listener.provider.SensorListenerProvider;
import michael.linker.gestrudeid.sensor.listener.suppressor.ISensorListenerSuppressor;
import michael.linker.gestrudeid.sensor.listener.suppressor.SensorListenerSuppressor;
import michael.linker.gestrudeid.sensor.listener.suppressor.SensorListenerSuppressorNotFoundException;
import michael.linker.gestrudeid.sensor.provider.ISensorProvider;
import michael.linker.gestrudeid.sensor.provider.SensorProvider;
import michael.linker.gestrudeid.sensor.provider.SensorProviderNotFoundException;
import michael.linker.gestrudeid.sensor.type.SensorType;
import michael.linker.gestrudeid.sensor.wrapper.manager.ASensorManager;
import michael.linker.gestrudeid.sensor.wrapper.sensor.SensorWrapper;
import michael.linker.gestrudeid.stream.manager.IStreamManager;
import michael.linker.gestrudeid.stream.manager.StreamManager;
import michael.linker.gestrudeid.stream.manager.StreamManagerFailedException;
import michael.linker.gestrudeid.stream.manager.StreamManagerNotFoundException;
import michael.linker.gestrudeid.stream.output.model.AOutputStreamModel;
import michael.linker.gestrudeid.stream.output.stream.IOutputStream;
import michael.linker.gestrudeid.synchronizer.EventSynchronizer;
import michael.linker.gestrudeid.synchronizer.EventSynchronizerFailedException;
import michael.linker.gestrudeid.synchronizer.IEventSynchronizer;
import michael.linker.gestrudeid.world.exception.WorldFailedException;

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
            closeOpenedOutputStream();
            outputStream = streamManager.getOutputStream(outputStreamModel);
            formatter.setNewOutputStream(outputStream);
        } catch (StreamManagerNotFoundException | StreamManagerFailedException e) {
            throw new WorldFailedException("It is not possible to create a new output stream!", e);
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
        closeOpenedOutputStream();
        // TODO: Close input stream
    }

    private void closeOpenedOutputStream() {
        if (outputStream != null) {
            outputStream.close();
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
