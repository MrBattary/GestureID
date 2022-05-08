package michael.linker.gestrudeid.streams.provider;

import michael.linker.gestrudeid.streams.output.stream.IOutputStream;

/**
 * Provider for the output/input streams
 */
public interface IStreamsProvider {
    /**
     * Returns a output stream for the sensors data
     * According to the configuration, the primary stream is returned,
     * if it fails, the backup one is returned
     *
     * @return Output sensor stream
     * @throws StreamsProviderNotFoundException If the primary and backup streams cannot be
     * returned
     */
    IOutputStream getOutputStream() throws StreamsProviderNotFoundException;
}
