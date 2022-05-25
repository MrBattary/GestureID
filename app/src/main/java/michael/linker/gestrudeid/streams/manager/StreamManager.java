package michael.linker.gestrudeid.streams.manager;

import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import michael.linker.gestrudeid.config.StreamsBuildConfiguration;
import michael.linker.gestrudeid.streams.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.streams.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestrudeid.streams.output.factory.impl.LogOutputStreamFactory;
import michael.linker.gestrudeid.streams.output.factory.impl.UiOutputStreamFactory;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;
import michael.linker.gestrudeid.streams.output.stream.impl.UiOutputStream;
import michael.linker.gestrudeid.streams.output.types.OutputStreamType;

public class StreamManager implements IStreamManager {
    private final static String TAG = StreamManager.class.getCanonicalName();
    private final Map<OutputStreamType, IOutputStreamFactory> sensorStreamFactories =
            new HashMap<>();

    /**
     * Default constructor
     *
     * @param textView UI element for the UiSensorOutputStream
     * @see UiOutputStream
     */
    public StreamManager(final TextView textView) {
        sensorStreamFactories.put(OutputStreamType.LOGGER, new LogOutputStreamFactory());
        sensorStreamFactories.put(OutputStreamType.UI, new UiOutputStreamFactory(textView));
    }

    @Override
    public IOutputStream getOutputStream() throws StreamManagerNotFoundException {
        try {
            return getStreamByKey(StreamsBuildConfiguration.getMainOutputStreamType());
        } catch (OutputStreamFactoryFailedException e) {
            Log.w(TAG, e.getMessage());
            Log.w(TAG, "The main output stream is not available, "
                    + "switching to the backup is being performed");
            try {
                return getStreamByKey(StreamsBuildConfiguration.getBackupOutputStreamType());
            } catch (OutputStreamFactoryFailedException ee) {
                Log.w(TAG, e.getMessage());
                throw new StreamManagerFailedException(
                        "The main and backup output streams are not available!");
            }
        }
    }

    private IOutputStream getStreamByKey(final OutputStreamType streamType)
            throws StreamManagerNotFoundException {
        IOutputStreamFactory sensorStreamFactory = sensorStreamFactories.get(streamType);
        try {
            return Objects.requireNonNull(sensorStreamFactory).getOutputStream();
        } catch (OutputStreamFactoryFailedException e) {
            Log.e(TAG, e.getMessage());
            throw new StreamManagerNotFoundException("Required output stream with type: "
                    + streamType.toString() + " was not found!", e);
        }
    }
}
