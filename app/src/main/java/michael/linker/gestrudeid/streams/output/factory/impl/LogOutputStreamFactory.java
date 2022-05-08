package michael.linker.gestrudeid.streams.output.factory.impl;

import michael.linker.gestrudeid.streams.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.streams.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;
import michael.linker.gestrudeid.streams.output.stream.impl.LogOutputStream;

/**
 * The logger stream factory
 */
public class LogOutputStreamFactory implements IOutputStreamFactory {
    @Override
    public IOutputStream getOutputStream() throws OutputStreamFactoryFailedException {
        return new LogOutputStream();
    }
}
