package michael.linker.gestureid.stream.output.factory.impl;

import michael.linker.gestureid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestureid.stream.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestureid.stream.output.stream.IOutputStream;
import michael.linker.gestureid.stream.output.stream.impl.LogOutputStream;

/**
 * The logger stream factory
 */
public class LogOutputStreamFactory implements IOutputStreamFactory {
    private LogOutputStream logOutputStream;

    @Override
    public IOutputStream getOutputStream() throws OutputStreamFactoryFailedException {
        if (logOutputStream == null) {
            logOutputStream = new LogOutputStream();
        }
        return logOutputStream;
    }
}
