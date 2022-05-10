package michael.linker.gestrudeid.streams.output.factory.impl;

import android.widget.TextView;

import michael.linker.gestrudeid.streams.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.streams.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;
import michael.linker.gestrudeid.streams.output.stream.impl.UiOutputStream;

/**
 * The UI stream factory
 */
public class UiOutputStreamFactory implements IOutputStreamFactory {
    private final TextView textView;
    private UiOutputStream uiOutputStream;

    public UiOutputStreamFactory(TextView textView) {
        this.textView = textView;
    }

    @Override
    public IOutputStream getOutputStream() throws OutputStreamFactoryFailedException {
        if (uiOutputStream == null) {
            if (textView == null) {
                throw new OutputStreamFactoryFailedException(
                        "Impossible to open an UI output stream, the UI element is not provided!");
            } else {
                uiOutputStream = new UiOutputStream(textView);
            }
        }
        return uiOutputStream;
    }
}
