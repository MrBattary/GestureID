package michael.linker.gestrudeid.sensor.output.stream;

import android.widget.TextView;

/**
 * This stream uses UI element to write the data into it
 * Current UI element - TextView
 */
public class UiSensorStream implements ISensorStream {
    private final TextView textView;

    public UiSensorStream(TextView view) {
        this.textView = view;
    }

    @Override
    public void write(String s) {
        textView.append(s + "\n");
    }
}
