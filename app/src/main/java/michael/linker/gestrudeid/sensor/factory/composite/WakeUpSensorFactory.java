package michael.linker.gestrudeid.sensor.factory.composite;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import michael.linker.gestrudeid.sensor.factory.ISensorFactory;
import michael.linker.gestrudeid.sensor.factory.SensorNotFoundException;
import michael.linker.gestrudeid.sensor.tag.category.Interaction;

/**
 * Returns an Wake Up sensor implementation
 */
public class WakeUpSensorFactory implements ISensorFactory {
    @Interaction
    private final Sensor wakeUpSensorImplementation;

    public WakeUpSensorFactory(final SensorManager sensorManager) {
        wakeUpSensorImplementation = sensorManager.getDefaultSensor(
                Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public Sensor getImplementation() throws SensorNotFoundException {
        if (wakeUpSensorImplementation != null) {
            return wakeUpSensorImplementation;
        } else {
            throw new SensorNotFoundException("An available wake up sensor was not found!");
        }
    }
}
