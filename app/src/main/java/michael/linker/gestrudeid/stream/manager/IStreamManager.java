package michael.linker.gestrudeid.stream.manager;

import michael.linker.gestrudeid.stream.output.model.AOutputStreamModel;
import michael.linker.gestrudeid.stream.output.stream.IOutputStream;

/**
 * Manager for the output/input streams
 */
public interface IStreamManager {
    /**
     * Returns a output stream for the sensors data
     *
     * @param streamModel Model for stream that contains all necessary data
     * @return Output sensor stream
     * @throws StreamManagerNotFoundException If the required resources was not found
     * @throws StreamManagerFailedException If the internal error occurs
     */
    IOutputStream getOutputStream(AOutputStreamModel streamModel)
            throws StreamManagerNotFoundException, StreamManagerFailedException;
}
