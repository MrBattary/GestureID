package michael.linker.gestrudeid.stream.output.model;

import android.widget.TextView;

import michael.linker.gestrudeid.stream.output.type.OutputStreamType;

/**
 * Model that uses UI Output Stream components
 */
public class UiOutputModel extends AOutputStreamModel {
    private final TextView textView;

    /**
     * Default constructor
     *
     * @param textView UI Text Component
     */
    public UiOutputModel(TextView textView) {
        super(OutputStreamType.UI);
        this.textView = textView;
    }

    public TextView getTextView() {
        return textView;
    }
}
