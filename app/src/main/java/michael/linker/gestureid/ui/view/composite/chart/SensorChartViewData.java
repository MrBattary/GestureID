package michael.linker.gestureid.ui.view.composite.chart;

import michael.linker.gestureid.core.sensor.sensor.type.SensorType;

public class SensorChartViewData {
    private final SensorType sensorType;
    private final String headingText, headingNoContentText;

    public SensorChartViewData(
            SensorType sensorType,
            String headingText,
            String headingNoContentText) {
        this.sensorType = sensorType;
        this.headingText = headingText;
        this.headingNoContentText = headingNoContentText;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public String getHeadingText() {
        return headingText;
    }

    public String getHeadingNoContentText() {
        return headingNoContentText;
    }
}
