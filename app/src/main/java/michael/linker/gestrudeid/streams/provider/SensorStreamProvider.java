package michael.linker.gestrudeid.streams.provider;

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
import michael.linker.gestrudeid.streams.output.type.OutputStreamType;

public class SensorStreamProvider implements ISensorStreamProvider {
    private final static String TAG = SensorStreamProvider.class.getCanonicalName();
    private final Map<OutputStreamType, IOutputStreamFactory> sensorStreamFactories =
            new HashMap<>();

    /**
     * Default constructor
     *
     * @param textView UI element for the UiSensorOutputStream
     * @see UiOutputStream
     */
    public SensorStreamProvider(final TextView textView) {
        sensorStreamFactories.put(OutputStreamType.LOGGER, new LogOutputStreamFactory());
        sensorStreamFactories.put(OutputStreamType.UI, new UiOutputStreamFactory(textView));
    }

    @Override
    public IOutputStream getOutputStream() throws SensorStreamConfiguratorNotFoundException {
        try {
            return getStreamByKey(StreamsBuildConfiguration.getMainOutputStreamType());
        } catch (OutputStreamFactoryFailedException e) {
            Log.e(TAG, "The primary output stream is not available, "
                    + "switching to the backup is being performed");
            return getStreamByKey(StreamsBuildConfiguration.getBackupOutputStreamType());
        }
    }

    private IOutputStream getStreamByKey(final OutputStreamType streamType)
            throws SensorStreamConfiguratorNotFoundException {
        IOutputStreamFactory sensorStreamFactory = sensorStreamFactories.get(streamType);
        try {
            return Objects.requireNonNull(sensorStreamFactory).getOutputStream();
        } catch (OutputStreamFactoryFailedException e) {
            Log.e(TAG, e.getMessage());
            throw new SensorStreamConfiguratorNotFoundException(
                    "Required output stream was not found!", e);
        }
    }
}
