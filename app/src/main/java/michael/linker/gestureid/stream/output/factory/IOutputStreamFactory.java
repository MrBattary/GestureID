package michael.linker.gestureid.stream.output.factory;

import michael.linker.gestureid.stream.output.stream.IOutputStream;

/**
 * Output stream factory
 */
public interface IOutputStreamFactory {
    /**
     * Get the output stream according to the configuration file
     *
     * @return Output stream
     * @throws OutputStreamFactoryFailedException If the output stream cannot be returned
     */
    IOutputStream getOutputStream() throws OutputStreamFactoryFailedException;
}
