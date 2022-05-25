package michael.linker.gestrudeid.sensor.formatter;

import java.util.List;

import michael.linker.gestrudeid.sensor.model.ASensorModel;

/**
 * Formats sensor event data according to the configuration file
 * Connects to the provided output stream
 */
public interface ISensorModelFormatter {
    /**
     * Format data from the model
     *
     * @param sensorModel Model containing sensor event data
     */
    void format(ASensorModel sensorModel);

    /**
     * Format data from the list of the models
     *
     * @param sensorModels List of models that containing sensor event data
     */
    void format(List<ASensorModel> sensorModels);
}
