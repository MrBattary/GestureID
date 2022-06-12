package michael.linker.gestrudeid.formatter.factory;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestrudeid.config.FormatterBuildConfiguration;
import michael.linker.gestrudeid.formatter.IFormatter;
import michael.linker.gestrudeid.formatter.impl.text.TextFormatter;
import michael.linker.gestrudeid.formatter.type.FormatterMode;

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
