package michael.linker.gestrudeid.sensor.tag.category;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Uncalibrated sensors provide more raw results and may include some bias
 * but also contain fewer "jumps" from corrections applied through calibration.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Uncalibrated {
}
