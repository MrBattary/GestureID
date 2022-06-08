package michael.linker.gestrudeid.stream.manager.builder.output;

import michael.linker.gestrudeid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.model.AOutputStreamModel;

/**
 * The base output stream factory that implements command pattern
 */
public interface IStreamManagerOutputFactoryBuilder {
    /**
     * Build factory by model
     *
     * @param outputStreamModel Model with OutputStreamType
     * @return Factory implementation
     */
    IOutputStreamFactory buildFactory(AOutputStreamModel outputStreamModel);
}
