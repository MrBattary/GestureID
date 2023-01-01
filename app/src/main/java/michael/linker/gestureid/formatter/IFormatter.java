package michael.linker.gestureid.formatter;

import michael.linker.gestureid.stream.output.stream.IOutputStream;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventSingleModel;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEventListOfModels;

/**
 * Formats sensor event data according to the configuration file
 * Connects to the provided output stream
 */
public interface IFormatter {
    /**
     * Format data from a single model
     *
     * @param synchronizedSensorModel Model containing timestamp and sensor event data
     */
    void format(SynchronizedEventSingleModel synchronizedSensorModel);

    /**
     * Format data from the list of the models
     *
     * @param synchronizedSensorModels Model that containing timestamp and list of sensor event data
     */
    void format(SynchronizedEventListOfModels synchronizedSensorModels);

    /**
     * Set a new output stream
     *
     * @param outputStream New output stream
     */
    void setNewOutputStream(IOutputStream outputStream);
}
