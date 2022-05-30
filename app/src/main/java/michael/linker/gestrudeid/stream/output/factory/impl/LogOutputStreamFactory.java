package michael.linker.gestrudeid.stream.output.factory.impl;

import michael.linker.gestrudeid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestrudeid.stream.output.stream.IOutputStream;
import michael.linker.gestrudeid.stream.output.stream.impl.LogOutputStream;

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
