package michael.linker.gestrudeid.sensor.tag.category;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Sensors that registers interaction with the phone
 *
 * @link https://source.android.com/devices/sensors/sensor-types#interaction_composite_sensors
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Interaction {
}
