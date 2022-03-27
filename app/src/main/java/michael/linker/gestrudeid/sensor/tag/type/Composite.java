package michael.linker.gestrudeid.sensor.tag.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A composite sensor generates data by processing and/or fusing data from one or several
 * physical @Base sensors.
 * Any sensor that isn't a base sensor is called a composite sensor.
 *
 * @link https://source.android.com/devices/sensors/sensor-types#composite_sensor_types
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Composite {
    // Sensors that always uses
    String[] required() default "";
    // Sensor that can be replaced with alternative if some error occurs
    String[] replaceable() default "";
    // Alternative sensors
    String[] alternative() default "";
}
