package michael.linker.gestureid.elements.fragment.sensors.root;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.sensor.model.ASensorModel;

public class SensorsViewModel extends ViewModel implements IActiveEventAccumulatorListener {
    private final MutableLiveData<String> timestamp;
    private final MutableLiveData<ASensorModel<Float>> accelerometerEvent;

    public SensorsViewModel() {
        timestamp = new MutableLiveData<>();
        accelerometerEvent = new MutableLiveData<>();
    }

    public MutableLiveData<String> getTimestamp() {
        return timestamp;
    }

    public LiveData<ASensorModel<Float>> getAccelerometerEvent() {
        return accelerometerEvent;
    }

    @Override
    public void notifyAboutEpisode(AccumulatedEpisode accumulatedEpisode) {
        for (SynchronizedEvent event : accumulatedEpisode.getData()) {
            timestamp.setValue(event.getTimestamp());
            for (ASensorModel<Float> sensorModel : event.getData()) {
                if (sensorModel.getSensorType() == BaseSensorType.ACCELEROMETER) {
                    accelerometerEvent.setValue(sensorModel);
                }
            }
        }
    }
}