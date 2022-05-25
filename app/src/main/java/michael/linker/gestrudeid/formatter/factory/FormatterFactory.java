package michael.linker.gestrudeid.formatter.factory;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import michael.linker.gestrudeid.config.FormatterBuildConfiguration;
import michael.linker.gestrudeid.formatter.IFormatter;
import michael.linker.gestrudeid.formatter.impl.TextFormatter;
import michael.linker.gestrudeid.formatter.types.FormatterMode;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;

public class FormatterFactory implements IFormatterFactory {
    private final static String TAG = FormatterFactory.class.getCanonicalName();
    private final Map<FormatterMode, IFormatter> formatters = new HashMap<>();

    public FormatterFactory(IOutputStream outputStream) {
        formatters.put(FormatterMode.TEXT, new TextFormatter(outputStream));
        // TODO: formatters.put(FormatterMode.DB, new DatabaseFormatter(outputStream));
    }

    @Override
    public IFormatter getFormatter() throws FormatterFactoryFailedException {
        try {
            return getFormatterByKey(FormatterBuildConfiguration.getMainFormatterMode());
        } catch (FormatterFactoryNotFoundException e) {
            Log.w(TAG, e.getMessage());
            Log.w(TAG, "The main formatter is not available, "
                    + "switching to the backup is being performed.");
            try {
                return getFormatterByKey(FormatterBuildConfiguration.getBackupFormatterMode());
            } catch (FormatterFactoryNotFoundException ee) {
                Log.w(TAG, ee.getMessage());
                throw new FormatterFactoryFailedException(
                        "The main and backup formatters are not available!");
            }
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
