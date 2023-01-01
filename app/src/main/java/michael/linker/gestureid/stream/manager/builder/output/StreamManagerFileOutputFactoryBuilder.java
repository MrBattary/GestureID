package michael.linker.gestureid.stream.manager.builder.output;

import michael.linker.gestureid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestureid.stream.output.factory.impl.FileOutputStreamFactory;
import michael.linker.gestureid.stream.output.model.AOutputStreamModel;
import michael.linker.gestureid.stream.output.model.FileOutputModel;

public class StreamManagerFileOutputFactoryBuilder implements IStreamManagerOutputFactoryBuilder {
    @Override
    public IOutputStreamFactory buildFactory(AOutputStreamModel outputStreamModel) {
        return new FileOutputStreamFactory((FileOutputModel) outputStreamModel);
    }
}
