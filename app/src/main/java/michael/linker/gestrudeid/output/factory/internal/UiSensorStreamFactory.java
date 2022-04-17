package michael.linker.gestrudeid.output.factory.internal;

import android.widget.TextView;

import michael.linker.gestrudeid.output.factory.ISensorStreamFactory;
import michael.linker.gestrudeid.output.factory.SensorStreamFactoryFailedException;
import michael.linker.gestrudeid.output.stream.ISensorStream;
import michael.linker.gestrudeid.output.stream.internal.UiSensorStream;

/**
 * The UI stream factory
 */
public class UiSensorStreamFactory implements ISensorStreamFactory {
    private final TextView textView;

    public UiSensorStreamFactory(TextView textView) {
        this.textView = textView;
    }

    @Override
    public ISensorStream getStream() throws SensorStreamFactoryFailedException {
        return new UiSensorStream(this.textView);
    }
}
