package michael.linker.gestrudeid.stream.output.factory.impl;

import android.widget.TextView;

import michael.linker.gestrudeid.stream.output.factory.IOutputStreamFactory;
import michael.linker.gestrudeid.stream.output.factory.OutputStreamFactoryFailedException;
import michael.linker.gestrudeid.stream.output.model.UiOutputModel;
import michael.linker.gestrudeid.stream.output.stream.IOutputStream;
import michael.linker.gestrudeid.stream.output.stream.impl.UiOutputStream;

/**
 * The UI stream factory
 */
public class UiOutputStreamFactory implements IOutputStreamFactory {
    private final UiOutputModel uiOutputModel;
    private UiOutputStream uiOutputStream;

    public UiOutputStreamFactory(UiOutputModel uiOutputModel) {
        this.uiOutputModel = uiOutputModel;
    }

    @Override
    public IOutputStream getOutputStream() throws OutputStreamFactoryFailedException {
        final TextView textView = uiOutputModel.getTextView();
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
