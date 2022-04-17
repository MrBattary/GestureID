package michael.linker.gestrudeid.output.config;

import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import michael.linker.gestrudeid.config.StreamsBuildConfiguration;
import michael.linker.gestrudeid.output.factory.ISensorStreamFactory;
import michael.linker.gestrudeid.output.factory.SensorStreamFactoryFailedException;
import michael.linker.gestrudeid.output.factory.internal.LogSensorStreamFactory;
import michael.linker.gestrudeid.output.factory.internal.UiSensorStreamFactory;
import michael.linker.gestrudeid.output.stream.ISensorStream;
import michael.linker.gestrudeid.output.types.SensorStreamType;

public class SensorStreamConfiguration implements ISensorStreamConfiguration {
    private final static String TAG = SensorStreamConfiguration.class.getCanonicalName();
    private final Map<SensorStreamType, ISensorStreamFactory> sensorStreamFactories =
            new HashMap<>();

    /**
     * Default constructor
     *
     * @param textView UI element for the UiSensorStream
     * @see michael.linker.gestrudeid.output.stream.internal.UiSensorStream
     */
    public SensorStreamConfiguration(final TextView textView) {
        sensorStreamFactories.put(SensorStreamType.LOGGER, new LogSensorStreamFactory());
        sensorStreamFactories.put(SensorStreamType.UI, new UiSensorStreamFactory(textView));
    }

    @Override
    public ISensorStream getOutputStream() throws SensorStreamConfigurationNotFoundException {
        try {
            return getStreamByKey(StreamsBuildConfiguration.getMainOutputStreamType());
        } catch (SensorStreamFactoryFailedException e) {
            Log.e(TAG, "The primary output stream is not available, "
                    + "switching to the backup is being performed");
            return getStreamByKey(StreamsBuildConfiguration.getBackupOutputStreamType());
        }
    }

    private ISensorStream getStreamByKey(final SensorStreamType streamType)
            throws SensorStreamConfigurationNotFoundException {
        ISensorStreamFactory sensorStreamFactory = sensorStreamFactories.get(streamType);
        try {
            return Objects.requireNonNull(sensorStreamFactory).getStream();
        } catch (SensorStreamFactoryFailedException e) {
            Log.e(TAG, e.getMessage());
            throw new SensorStreamConfigurationNotFoundException(
                    "Required output stream was not found!", e);
        }
    }
}
