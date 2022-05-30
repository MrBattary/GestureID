package michael.linker.gestrudeid.synchronizer.model;

import michael.linker.gestrudeid.sensor.model.ASensorModel;

/**
 * Wrapper for SynchronizedEvent with only one model
 */
public class SynchronizedEventOneModel extends SynchronizedEvent {
    private final ASensorModel sensorModel;

    public SynchronizedEventOneModel(final String timestamp,
                                     final ASensorModel sensorModel) {
        super(timestamp);
        this.sensorModel = sensorModel;
    }

    public ASensorModel getSensorModel() {
        return sensorModel;
    }
}
