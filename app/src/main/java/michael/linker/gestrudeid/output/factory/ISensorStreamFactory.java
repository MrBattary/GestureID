package michael.linker.gestrudeid.output.factory;

import michael.linker.gestrudeid.output.stream.ISensorStream;

/**
 * Output stream factory
 */
public interface ISensorStreamFactory {
    /**
     * Get the output stream according to the configuration file
     *
     * @return Output stream
     * @throws SensorStreamFactoryFailedException If the output stream cannot be returned
     */
    ISensorStream getStream() throws SensorStreamFactoryFailedException;
}
