package michael.linker.gestrudeid.sensor.factory;

import android.hardware.Sensor;

public interface ISensorFactory {
    /**
     * Get implementation from the device sensor manager
     *
     * @return Implementation of a specific sensor
     * @throws SensorNotFoundException If any implementation was not found
     */
    Sensor getImplementation() throws SensorNotFoundException;
}
