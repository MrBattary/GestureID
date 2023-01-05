package michael.linker.gestureid.elements.component.sensor;

import android.view.View;

import com.google.android.material.textview.MaterialTextView;

import michael.linker.gestureid.R;

public class StubSensorComponent {
    private final MaterialTextView sensorNameView;
    private final MaterialTextView sensorStatusView;
    private final MaterialTextView sensorDataView;

    public StubSensorComponent(View sensorComponentView) {
        sensorNameView = sensorComponentView.findViewById(R.id.sensor_header_name);
        sensorStatusView = sensorComponentView.findViewById(R.id.sensor_header_status);
        sensorDataView = sensorComponentView.findViewById(R.id.sensor_data_text);
    }

    public void setName(String name) {
        sensorNameView.setText(name);
    }

    public void setStatus(String status) {
        sensorStatusView.setText(status);
    }

    public void setSensorData(String data) {
        sensorDataView.setText(data);
    }
}
