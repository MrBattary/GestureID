package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.CompositeSensorType;

/**
 * Returns a Gravity sensor implementation
 */
public class GravitySensorFactory implements ISensorFactory {

    private final Sensor gravityImplementation;

    public GravitySensorFactory(final SensorManager sensorManager) {
        gravityImplementation = sensorManager.getDefaultSensor(CompositeSensorType.GRAVITY);
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (gravityImplementation != null) {
            return gravityImplementation;
        } else {
            throw new SensorNotFoundException("An available gravity sensor was not found!");
        }
    }
}