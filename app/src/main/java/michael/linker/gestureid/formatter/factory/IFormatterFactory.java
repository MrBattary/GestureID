package michael.linker.gestureid.formatter.factory;

import michael.linker.gestureid.formatter.IFormatter;

/**
 * Factory pattern for the Formatter implementations
 */
public interface IFormatterFactory {
    /**
     * According to the configuration, the primary formatter is returned,
     * if it fails, the backup one is returned
     *
     * @return Formatter implementation
     * @throws FormatterFactoryFailedException If the primary and backup formatters cannot ne
     * returned
     */
    IFormatter getFormatter() throws FormatterFactoryFailedException;
}
