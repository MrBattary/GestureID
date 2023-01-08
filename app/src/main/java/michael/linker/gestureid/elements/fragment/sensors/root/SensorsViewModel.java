package michael.linker.gestureid.elements.fragment.sensors.root;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;
import michael.linker.gestureid.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.sensor.model.ASensorModel;

public class SensorsViewModel extends ViewModel implements IActiveEventAccumulatorListener {
    private final MutableLiveData<String> timestamp;
    private final MutableLiveData<ASensorModel<Float>> accelerometerEvent, magnetometerEvent,
            gyroscopeEvent, gravityEvent, linearAccelerationEvent, rotationVectorEvent,
            geoRotationVectorEvent;

    public SensorsViewModel() {
        timestamp = new MutableLiveData<>();
        accelerometerEvent = new MutableLiveData<>();
        magnetometerEvent = new MutableLiveData<>();
        gyroscopeEvent = new MutableLiveData<>();
        gravityEvent = new MutableLiveData<>();
        linearAccelerationEvent = new MutableLiveData<>();
        rotationVectorEvent = new MutableLiveData<>();
        geoRotationVectorEvent = new MutableLiveData<>();
    }

    public LiveData<String> getTimestamp() {
        return timestamp;
    }

    public LiveData<ASensorModel<Float>> getAccelerometerEvent() {
        return accelerometerEvent;
    }

    public LiveData<ASensorModel<Float>> getMagnetometerEvent() {
        return magnetometerEvent;
    }

    public LiveData<ASensorModel<Float>> getGyroscopeEvent() {
        return gyroscopeEvent;
    }

    public LiveData<ASensorModel<Float>> getGravityEvent() {
        return gravityEvent;
    }

    public LiveData<ASensorModel<Float>> getLinearAccelerationEvent() {
        return linearAccelerationEvent;
    }

    public LiveData<ASensorModel<Float>> getRotationVectorEvent() {
        return rotationVectorEvent;
    }

    public LiveData<ASensorModel<Float>> getGeoRotationVectorEvent() {
        return geoRotationVectorEvent;
    }

    @Override
    public void notifyAboutEpisode(AccumulatedEpisode accumulatedEpisode) {
        for (SynchronizedEvent event : accumulatedEpisode.getData()) {
            timestamp.postValue(event.getTimestamp());
            for (ASensorModel<Float> sensorModel : event.getData()) {
                if (sensorModel.getSensorType() == BaseSensorType.ACCELEROMETER) {
                    accelerometerEvent.postValue(sensorModel);
                } else if (sensorModel.getSensorType() == BaseSensorType.GYROSCOPE) {
                    gyroscopeEvent.postValue(sensorModel);
                } else if (sensorModel.getSensorType() == BaseSensorType.MAGNETOMETER) {
                    magnetometerEvent.postValue(sensorModel);
                } else if (sensorModel.getSensorType() == CompositeSensorType.GRAVITY) {
                    gravityEvent.postValue(sensorModel);
                } else if (sensorModel.getSensorType() == CompositeSensorType.LINEAR_ACCELERATION) {
                    linearAccelerationEvent.postValue(sensorModel);
                } else if (sensorModel.getSensorType() == CompositeSensorType.ROTATION_VECTOR) {
                    rotationVectorEvent.postValue(sensorModel);
                } else if (sensorModel.getSensorType() == CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR) {
                    geoRotationVectorEvent.postValue(sensorModel);
                }
            }
        }
    }
}