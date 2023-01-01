package michael.linker.gestureid.formatter.factory;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestureid.config.FormatterBuildConfiguration;
import michael.linker.gestureid.formatter.IFormatter;
import michael.linker.gestureid.formatter.impl.text.TextFormatter;
import michael.linker.gestureid.formatter.type.FormatterMode;

public class FormatterFactory implements IFormatterFactory {
    private final static String TAG = FormatterFactory.class.getCanonicalName();
    private final Map<FormatterMode, IFormatter> formatters = new HashMap<>();

    public FormatterFactory() {
        formatters.put(FormatterMode.TEXT, new TextFormatter());
        // TODO: formatters.put(FormatterMode.DB, new DatabaseFormatter(outputStream));
    }

    @Override
    public IFormatter getFormatter() throws FormatterFactoryFailedException {
        try {
            return getFormatterByKey(FormatterBuildConfiguration.getFormatterMode());
        } catch (FormatterFactoryNotFoundException e) {
            throw new FormatterFactoryFailedException("Could not get the requested formatter!", e);
        }
    }

    private IFormatter getFormatterByKey(FormatterMode formatterMode)
            throws FormatterFactoryNotFoundException {
        IFormatter formatter = formatters.get(formatterMode);
        if (formatter == null) {
            throw new FormatterFactoryNotFoundException("Required formatter with type: "
                    + formatterMode.toString() + " was not found!");
        }
        return formatter;
    }
}
