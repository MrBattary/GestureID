package michael.linker.gestureid.stream.manager.builder.output;

import michael.linker.gestureid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestureid.stream.output.factory.impl.UiOutputStreamFactory;
import michael.linker.gestureid.stream.output.model.AOutputStreamModel;
import michael.linker.gestureid.stream.output.model.UiOutputModel;

public class StreamManagerUiOutputFactoryBuilder implements IStreamManagerOutputFactoryBuilder {

    @Override
    public IOutputStreamFactory buildFactory(AOutputStreamModel outputStreamModel) {
        return new UiOutputStreamFactory((UiOutputModel) outputStreamModel);
    }
}
