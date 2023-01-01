package michael.linker.gestureid.config;

import michael.linker.gestureid.formatter.type.FormatterMode;

/**
 * Wrapper for the formatter build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestureid.formatter
 */
// TODO: STUBS!
public final class FormatterBuildConfiguration {
    public static FormatterMode getFormatterMode() {
        return null;
        //return BuildConfig.FORMATTER_MODE;
    }

    public static char getEventsSeparator() {
        return '/';
        //return BuildConfig.EVENTS_SEPARATOR;
    }

    public static String getModelsSeparator() {
        return null;
        //return BuildConfig.MODELS_SEPARATOR;
    }

    public static String getModelValuesSeparator() {
        return null;
        //return BuildConfig.MODEL_VALUES_SEPARATOR;
    }

    public static String getHeadingSeparator() {
        return null;
        //return BuildConfig.HEADING_SEPARATOR;
    }

    public static String getHeadingValueSeparator() {
        return null;
        //return BuildConfig.HEADING_VALUE_SEPARATOR;
    }

    public static String getHeadingTimestamp() {
        return null;
        //return BuildConfig.HEADING_TIMESTAMP;
    }
}
