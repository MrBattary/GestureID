package michael.linker.gestrudeid.formatter;

import michael.linker.gestrudeid.synchronizer.model.SynchronizedEventOneModel;
import michael.linker.gestrudeid.synchronizer.model.SynchronizedEventListOfModels;

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
    void format(SynchronizedEventOneModel synchronizedSensorModel);

    /**
     * Format data from the list of the models
     *
     * @param synchronizedSensorModels Model that containing timestamp and list of sensor event data
     */
    void format(SynchronizedEventListOfModels synchronizedSensorModels);
}
