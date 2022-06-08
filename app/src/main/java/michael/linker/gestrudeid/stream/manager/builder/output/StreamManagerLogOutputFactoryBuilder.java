package michael.linker.gestrudeid.stream.manager.builder.output;

import michael.linker.gestrudeid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.factory.impl.LogOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.model.AOutputStreamModel;

public class StreamManagerLogOutputFactoryBuilder implements IStreamManagerOutputFactoryBuilder {
    @Override
    public IOutputStreamFactory buildFactory(AOutputStreamModel outputStreamModel) {
        return new LogOutputStreamFactory();
    }
}
