package michael.linker.gestureid.stream.manager.builder.output;

import michael.linker.gestureid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestureid.stream.output.factory.impl.LogOutputStreamFactory;
import michael.linker.gestureid.stream.output.model.AOutputStreamModel;

public class StreamManagerLogOutputFactoryBuilder implements IStreamManagerOutputFactoryBuilder {
    @Override
    public IOutputStreamFactory buildFactory(AOutputStreamModel outputStreamModel) {
        return new LogOutputStreamFactory();
    }
}
