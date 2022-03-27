package michael.linker.gestrudeid.sensor.tag;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Info about sensor
 *
 * @link https://source.android.com/devices/sensors/sensor-types
 */
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface SensorInformation {
    Class<? extends Annotation>[] type() default {};
    Class<? extends Annotation>[] category() default {};
}
