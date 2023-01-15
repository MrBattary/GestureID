package michael.linker.gestureid.ui.fragment.sensors.root;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import michael.linker.gestureid.core.sensor.sensor.type.BaseSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.CompositeSensorType;
import michael.linker.gestureid.core.sensor.sensor.type.SensorType;
import michael.linker.gestureid.data.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.data.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.data.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.data.sensor.model.ASensorModel;

public class SensorsViewModel extends ViewModel implements IActiveEventAccumulatorListener {
    private final MutableLiveData<Double> timestamp;
    private final MutableLiveData<List<ASensorModel<Double>>> accelerometerEvent, magnetometerEvent,
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

    public LiveData<Double> getTimestamp() {
        return timestamp;
    }

    public LiveData<List<ASensorModel<Double>>> getAccelerometerEvent() {
        return accelerometerEvent;
    }

    public LiveData<List<ASensorModel<Double>>> getMagnetometerEvent() {
        return magnetometerEvent;
    }

    public LiveData<List<ASensorModel<Double>>> getGyroscopeEvent() {
        return gyroscopeEvent;
    }

    public LiveData<List<ASensorModel<Double>>> getGravityEvent() {
        return gravityEvent;
    }

    public LiveData<List<ASensorModel<Double>>> getLinearAccelerationEvent() {
        return linearAccelerationEvent;
    }

    public LiveData<List<ASensorModel<Double>>> getRotationVectorEvent() {
        return rotationVectorEvent;
    }

    public LiveData<List<ASensorModel<Double>>> getGeoRotationVectorEvent() {
        return geoRotationVectorEvent;
    }

    @Override
    public void notifyAboutEpisode(AccumulatedEpisode accumulatedEpisode) {
        Map<SensorType, List<ASensorModel<Double>>> modelsPerSensorFromEpisodeMap =
                new HashMap<>();
        List<SynchronizedEvent> eventList = accumulatedEpisode.getData();
        if (eventList.size() >= 1) {
            timestamp.postValue(eventList.get(0).getTimestamp());
        }
        for (SynchronizedEvent event : eventList) {
            for (ASensorModel<Double> sensorModel : event.getData()) {
                if (!modelsPerSensorFromEpisodeMap.containsKey(sensorModel.getSensorType())) {
                    modelsPerSensorFromEpisodeMap.put(sensorModel.getSensorType(),
                            new ArrayList<>());
                }
                List<ASensorModel<Double>> sensorModelList =
                        modelsPerSensorFromEpisodeMap.get(sensorModel.getSensorType());
                sensorModelList.add(sensorModel);
            }
        }

        for (SensorType sensorType : modelsPerSensorFromEpisodeMap.keySet()) {
            List<ASensorModel<Double>> sensorModelList =
                    modelsPerSensorFromEpisodeMap.get(sensorType);
            if (sensorType == BaseSensorType.ACCELEROMETER) {
                accelerometerEvent.postValue(sensorModelList);
            } else if (sensorType == BaseSensorType.GYROSCOPE) {
                gyroscopeEvent.postValue(sensorModelList);
            } else if (sensorType == BaseSensorType.MAGNETOMETER) {
                magnetometerEvent.postValue(sensorModelList);
            } else if (sensorType == CompositeSensorType.GRAVITY) {
                gravityEvent.postValue(sensorModelList);
            } else if (sensorType == CompositeSensorType.LINEAR_ACCELERATION) {
                linearAccelerationEvent.postValue(sensorModelList);
            } else if (sensorType == CompositeSensorType.ROTATION_VECTOR) {
                rotationVectorEvent.postValue(sensorModelList);
            } else if (sensorType
                    == CompositeSensorType.GEOMAGNETIC_ROTATION_VECTOR) {
                geoRotationVectorEvent.postValue(sensorModelList);
            }
        }
    }
}