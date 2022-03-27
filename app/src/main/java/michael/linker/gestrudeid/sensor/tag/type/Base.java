package michael.linker.gestrudeid.sensor.tag.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Base sensor types are named after the physical sensors they represent.
 * These sensors relay data from a single physical sensor
 * (as opposed to composite sensors that generate data out of other sensors).
 *
 * @link https://source.android.com/devices/sensors/sensor-types#base_sensors
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Base {
}
