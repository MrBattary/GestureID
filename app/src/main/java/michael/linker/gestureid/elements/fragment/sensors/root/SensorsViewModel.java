package michael.linker.gestureid.elements.fragment.sensors.root;

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
import michael.linker.gestureid.event.accumulator.mode.active.IActiveEventAccumulatorListener;
import michael.linker.gestureid.event.accumulator.model.AccumulatedEpisode;
import michael.linker.gestureid.event.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.sensor.model.ASensorModel;

public class SensorsViewModel extends ViewModel implements IActiveEventAccumulatorListener {
    private final MutableLiveData<String> timestamp;
    private final MutableLiveData<List<ASensorModel<Float>>> accelerometerEvent, magnetometerEvent,
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

    public LiveData<List<ASensorModel<Float>>> getAccelerometerEvent() {
        return accelerometerEvent;
    }

    public LiveData<List<ASensorModel<Float>>> getMagnetometerEvent() {
        return magnetometerEvent;
    }

    public LiveData<List<ASensorModel<Float>>> getGyroscopeEvent() {
        return gyroscopeEvent;
    }

    public LiveData<List<ASensorModel<Float>>> getGravityEvent() {
        return gravityEvent;
    }

    public LiveData<List<ASensorModel<Float>>> getLinearAccelerationEvent() {
        return linearAccelerationEvent;
    }

    public LiveData<List<ASensorModel<Float>>> getRotationVectorEvent() {
        return rotationVectorEvent;
    }

    public LiveData<List<ASensorModel<Float>>> getGeoRotationVectorEvent() {
        return geoRotationVectorEvent;
    }

    @Override
    public void notifyAboutEpisode(AccumulatedEpisode accumulatedEpisode) {
        Map<SensorType, List<ASensorModel<Float>>> modelsPerSensorFromEpisodeMap =
                new HashMap<>();
        List<SynchronizedEvent> eventList = accumulatedEpisode.getData();
        if (eventList.size() >= 1) {
            timestamp.postValue(
                    Long.valueOf(
                            Long.parseLong(eventList.get(eventList.size() - 1).getTimestamp())
                            -
                            Long.parseLong(eventList.get(0).getTimestamp())
                    ).toString()
            );
        }
        for (SynchronizedEvent event : eventList) {
            for (ASensorModel<Float> sensorModel : event.getData()) {
                if (!modelsPerSensorFromEpisodeMap.containsKey(sensorModel.getSensorType())) {
                    modelsPerSensorFromEpisodeMap.put(sensorModel.getSensorType(),
                            new ArrayList<>());
                }
                List<ASensorModel<Float>> sensorModelList =
                        modelsPerSensorFromEpisodeMap.get(sensorModel.getSensorType());
                sensorModelList.add(sensorModel);
            }
        }

        for (SensorType sensorType : modelsPerSensorFromEpisodeMap.keySet()) {
            List<ASensorModel<Float>> sensorModelList =
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