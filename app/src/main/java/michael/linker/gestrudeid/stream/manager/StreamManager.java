package michael.linker.gestrudeid.stream.manager;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestrudeid.stream.manager.builder.output.IStreamManagerOutputFactoryBuilder;
import michael.linker.gestrudeid.stream.manager.builder.output.StreamManagerFileOutputFactoryBuilder;
import michael.linker.gestrudeid.stream.manager.builder.output.StreamManagerUiOutputFactoryBuilder;
import michael.linker.gestrudeid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestrudeid.stream.output.model.AOutputStreamModel;
import michael.linker.gestrudeid.stream.output.stream.IOutputStream;
import michael.linker.gestrudeid.stream.output.type.OutputStreamType;

public class StreamManager implements IStreamManager {
    private static final String TAG = StreamManager.class.getCanonicalName();
    private final Map<OutputStreamType, IStreamManagerOutputFactoryBuilder> outputFactories =
            new HashMap<>();

    public StreamManager() {
        outputFactories.put(OutputStreamType.LOGGER, new StreamManagerFileOutputFactoryBuilder());
        outputFactories.put(OutputStreamType.UI, new StreamManagerUiOutputFactoryBuilder());
        outputFactories.put(OutputStreamType.FILE, new StreamManagerFileOutputFactoryBuilder());
    }

    @Override
    public IOutputStream getOutputStream(final AOutputStreamModel streamModel)
            throws StreamManagerNotFoundException, StreamManagerFailedException {
        final OutputStreamType streamType = streamModel.getStreamType();
        IStreamManagerOutputFactoryBuilder streamManagerOutputFactory
                = getManagerOutputFactory(streamType);

        IOutputStreamFactory streamFactory = streamManagerOutputFactory.buildFactory(streamModel);

        try {
            return streamFactory.getOutputStream();
        } catch (OutputStreamFactoryFailedException e) {
            Log.e(TAG, e.getMessage());
            throw new StreamManagerNotFoundException("Required output stream with type: "
                    + streamType.toString() + " was not found!", e);
        }
    }

    private IStreamManagerOutputFactoryBuilder getManagerOutputFactory(OutputStreamType streamType)
            throws StreamManagerNotFoundException {
        IStreamManagerOutputFactoryBuilder factory = outputFactories.get(streamType);
        if (factory == null) {
            throw new StreamManagerNotFoundException(
                    "Required internal output stream factory with type: " + streamType.toString()
                            + " was not found!");
        }
        return factory;
    }
}
