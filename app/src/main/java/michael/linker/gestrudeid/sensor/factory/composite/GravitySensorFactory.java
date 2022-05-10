package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.config.SensorsBuildConfiguration;
import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotActivatedException;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.types.CompositeSensorType;
import michael.linker.gestrudeid.sensor.types.SensorType;

/**
 * Returns a Gravity sensor implementation
 */
public class GravitySensorFactory implements ISensorFactory {
    private static Sensor gravityImplementation;

    public GravitySensorFactory(final SensorManager sensorManager) {
        gravityImplementation = sensorManager.getDefaultSensor(CompositeSensorType.GRAVITY.toInt());
    }

    @Override
    public Sensor getActivatedImplementation()
            throws SensorNotActivatedException, SensorNotFoundException {
        if (SensorsBuildConfiguration.isGravityActivated()) {
            return this.getImplementation();
        } else {
            throw new SensorNotActivatedException("The gravity sensor is not activated!");
        }
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (gravityImplementation != null) {
            return gravityImplementation;
        } else {
            throw new SensorNotFoundException("An available gravity sensor was not found!");
        }
    }

    @Override
    public SensorType getSensorType() {
        return CompositeSensorType.GRAVITY;
    }
}