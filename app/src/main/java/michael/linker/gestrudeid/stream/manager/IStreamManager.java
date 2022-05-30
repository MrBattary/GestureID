package michael.linker.gestrudeid.stream.manager;

import michael.linker.gestrudeid.stream.output.stream.IOutputStream;

/**
 * Manager for the output/input streams
 */
public interface IStreamManager {
    /**
     * Returns a output stream for the sensors data
     * According to the configuration, the primary stream is returned,
     * if it fails, the backup one is returned
     *
     * @return Output sensor stream
     * @throws StreamManagerNotFoundException If the primary and backup streams cannot be
     * returned
     */
    IOutputStream getOutputStream() throws StreamManagerNotFoundException;
}
