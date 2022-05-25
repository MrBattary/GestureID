package michael.linker.gestrudeid.config;

import michael.linker.gestrudeid.BuildConfig;
import michael.linker.gestrudeid.formatter.types.FormatterMode;

/**
 * Wrapper for the formatter build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestrudeid.formatter
 */
public final class FormatterBuildConfiguration {
    public static FormatterMode getMainFormatterMode() {
        return BuildConfig.MAIN_FORMATTER_MODE;
    }

    public static FormatterMode getBackupFormatterMode() {
        return BuildConfig.BACKUP_FORMATTER_MODE;
    }

    public static char getEventsSeparator() {
        return BuildConfig.EVENTS_SEPARATOR;
    }

    public static String getModelsSeparator() {
        return BuildConfig.MODELS_SEPARATOR;
    }

    public static String getModelValuesSeparator() {
        return BuildConfig.MODEL_VALUES_SEPARATOR;
    }
}
