package michael.linker.gestrudeid.sensor.factory;

import android.hardware.Sensor;

/**
 * Default/stub sensor factory
 */
public class DefaultSensorFactory implements ISensorFactory {
    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        throw new SensorNotFoundException("Default sensor was not found");
    }
}
