package michael.linker.gestrudeid.stream.output.stream.impl;

import android.widget.TextView;

import michael.linker.gestrudeid.stream.output.stream.IOutputStream;

/**
 * This stream uses UI element to write the data into it
 * Current UI element - TextView
 */
public class UiOutputStream implements IOutputStream {
    private final TextView textView;

    public UiOutputStream(TextView view) {
        this.textView = view;
    }

    @Override
    public void write(String s) {
        textView.append(s);
    }
}
