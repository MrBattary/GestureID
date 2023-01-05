package michael.linker.gestureid.elements.fragment.sensors.root;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveEventBufferListener;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.sensor.model.ASensorModel;

public class SensorsViewModel extends ViewModel implements IActiveEventBufferListener {
    private final MutableLiveData<String> timestamp, accelerometerEvent, gyroscopeEvent,
            magnetometerEvent;

    public SensorsViewModel() {
        timestamp = new MutableLiveData<>();
        accelerometerEvent = new MutableLiveData<>();
        gyroscopeEvent = new MutableLiveData<>();
        magnetometerEvent = new MutableLiveData<>();
    }

    public MutableLiveData<String> getTimestamp() {
        return timestamp;
    }

    public LiveData<String> getAccelerometerEvent() {
        return accelerometerEvent;
    }

    public LiveData<String> getGyroscopeEvent() {
        return gyroscopeEvent;
    }

    public LiveData<String> getMagnetometerEvent() {
        return magnetometerEvent;
    }

    @Override
    public void notifyAboutEvents(List<SynchronizedEvent> synchronizedEventList) {
        for (SynchronizedEvent event : synchronizedEventList) {
            timestamp.postValue(event.getTimestamp());
            for (ASensorModel sensorModel : event.getData()) {
                if (sensorModel.getSensorType() == BaseSensorType.ACCELEROMETER) {
                    accelerometerEvent.postValue(sensorModel.getNamesAndValuesMap().toString());
                }
                if (sensorModel.getSensorType() == BaseSensorType.GYROSCOPE) {
                    gyroscopeEvent.postValue(sensorModel.getNamesAndValuesMap().toString());
                }
                if (sensorModel.getSensorType() == BaseSensorType.MAGNETOMETER) {
                    magnetometerEvent.postValue(sensorModel.getNamesAndValuesMap().toString());
                }
            }
        }
    }
}