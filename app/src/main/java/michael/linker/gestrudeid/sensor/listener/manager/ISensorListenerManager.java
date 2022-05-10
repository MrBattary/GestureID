package michael.linker.gestrudeid.sensor.listener.manager;

import michael.linker.gestrudeid.sensor.listener.ISensorListener;
import michael.linker.gestrudeid.sensor.model.SensorModel;
import michael.linker.gestrudeid.sensor.types.SensorType;

public interface ISensorListenerManager {
    /**
     * Returns a sensor listener by sensor type
     *
     * @param sensorType [Base/Composite]SensorType
     * @return SensorListener
     */
    ISensorListener getListener(SensorType sensorType);

    /**
     * Register an event from the sensor listener
     *
     * @param sensorModel Sensor data packed into the model
     */
    void registerEvent(SensorModel sensorModel);
}
