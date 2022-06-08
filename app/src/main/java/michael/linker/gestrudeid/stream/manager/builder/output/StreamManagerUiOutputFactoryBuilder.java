package michael.linker.gestrudeid.stream.manager.builder.output;

import michael.linker.gestrudeid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.factory.impl.UiOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.model.AOutputStreamModel;
import michael.linker.gestrudeid.stream.output.model.UiOutputModel;

public class StreamManagerUiOutputFactoryBuilder implements IStreamManagerOutputFactoryBuilder {

    @Override
    public IOutputStreamFactory buildFactory(AOutputStreamModel outputStreamModel) {
        return new UiOutputStreamFactory((UiOutputModel) outputStreamModel);
    }
}
