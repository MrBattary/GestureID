package michael.linker.gestrudeid.streams.output.factory.impl;

import michael.linker.gestrudeid.streams.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.streams.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;
import michael.linker.gestrudeid.streams.output.stream.impl.LogOutputStream;

/**
 * The logger stream factory
 */
public class LogOutputStreamFactory implements IOutputStreamFactory {
    private static LogOutputStream logOutputStream;

    public LogOutputStreamFactory() {
        logOutputStream = new LogOutputStream();
    }

    @Override
    public IOutputStream getOutputStream() throws OutputStreamFactoryFailedException {
        return logOutputStream;
    }
}
