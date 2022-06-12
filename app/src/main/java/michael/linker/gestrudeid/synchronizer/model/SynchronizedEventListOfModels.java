package michael.linker.gestrudeid.synchronizer.model;

import java.util.List;

import michael.linker.gestrudeid.sensor.model.ASensorModel;

/**
 * Wrapper for SynchronizedEvent with list of models
 */
public class SynchronizedEventListOfModels extends SynchronizedEvent {
    private final List<ASensorModel> sensorModels;

    public SynchronizedEventListOfModels(final String timestamp,
                                         final List<ASensorModel> sensorModels) {
        super(timestamp);
        this.sensorModels = sensorModels;
    }

    public List<ASensorModel> getSensorModels() {
        return sensorModels;
    }
}
