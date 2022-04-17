package michael.linker.gestrudeid.output.factory.internal;

import michael.linker.gestrudeid.output.factory.ISensorStreamFactory;
import michael.linker.gestrudeid.output.factory.SensorStreamFactoryFailedException;
import michael.linker.gestrudeid.output.stream.ISensorStream;
import michael.linker.gestrudeid.output.stream.internal.LogSensorStream;

/**
 * The logger stream factory
 */
public class LogSensorStreamFactory implements ISensorStreamFactory {
    @Override
    public ISensorStream getStream() throws SensorStreamFactoryFailedException {
        return new LogSensorStream();
    }
}
