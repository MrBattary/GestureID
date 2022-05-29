package michael.linker.gestrudeid.formatter.impl;

import java.util.ArrayList;
import java.util.List;

import michael.linker.gestrudeid.config.FormatterBuildConfiguration;
import michael.linker.gestrudeid.formatter.IFormatter;
import michael.linker.gestrudeid.sensor.model.ASensorModel;
import michael.linker.gestrudeid.streams.output.stream.IOutputStream;

public class TextFormatter implements IFormatter {
    private final IOutputStream outputStream;

    public TextFormatter(IOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void format(ASensorModel sensorModel) {
        final List<String> valuesAsStrings = new ArrayList<>();
        for (Float value : sensorModel.getValuesList()) {
            valuesAsStrings.add(value.toString());
        }
        final String combinedValues =
                String.join(FormatterBuildConfiguration.getModelValuesSeparator(), valuesAsStrings);

        outputStream.write(combinedValues.concat(FormatterBuildConfiguration.getModelsSeparator()));
    }

    @Override
    public void format(List<ASensorModel> sensorModels) {
        for (ASensorModel sensorModel : sensorModels) {
            this.format(sensorModel);
        }
        outputStream.write(Character.toString(FormatterBuildConfiguration.getEventsSeparator()));
    }
}
