package michael.linker.gestrudeid.stream.manager.builder.output;

import michael.linker.gestrudeid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.factory.impl.FileOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.model.AOutputStreamModel;
import michael.linker.gestrudeid.stream.output.model.FileOutputModel;

public class StreamManagerFileOutputFactoryBuilder implements IStreamManagerOutputFactoryBuilder {
    @Override
    public IOutputStreamFactory buildFactory(AOutputStreamModel outputStreamModel) {
        return new FileOutputStreamFactory((FileOutputModel) outputStreamModel);
    }
}
