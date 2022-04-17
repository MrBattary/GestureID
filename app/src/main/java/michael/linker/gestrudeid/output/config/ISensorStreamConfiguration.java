package michael.linker.gestrudeid.output.config;

import michael.linker.gestrudeid.output.stream.ISensorStream;

/**
 * Configurator for the output streams
 */
public interface ISensorStreamConfiguration {
    /**
     * Returns a output stream for the sensors data
     * According to the configuration, the primary stream is returned,
     * if it fails, the backup one is returned
     *
     * @return Output sensor stream
     * @throws SensorStreamConfigurationNotFoundException If the primary and backup streams cannot be
     * returned
     */
    ISensorStream getOutputStream() throws SensorStreamConfigurationNotFoundException;
}
