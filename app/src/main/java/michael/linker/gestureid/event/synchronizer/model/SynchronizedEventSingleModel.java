package michael.linker.gestureid.event.synchronizer.model;

import michael.linker.gestureid.sensor.model.ASensorModel;

/**
 * Wrapper for SynchronizedEvent with single model
 */
public class SynchronizedEventSingleModel extends SynchronizedEvent {
    private final ASensorModel sensorModel;

    public SynchronizedEventSingleModel(final String timestamp,
                                     final ASensorModel sensorModel) {
        super(timestamp);
        this.sensorModel = sensorModel;
    }

    public ASensorModel getSensorModel() {
        return sensorModel;
    }
}
