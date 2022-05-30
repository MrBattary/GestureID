package michael.linker.gestrudeid.formatter.impl.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import michael.linker.gestrudeid.config.FormatterBuildConfiguration;
import michael.linker.gestrudeid.formatter.IFormatter;
import michael.linker.gestrudeid.sensor.model.ASensorModel;
import michael.linker.gestrudeid.stream.output.stream.IOutputStream;
import michael.linker.gestrudeid.synchronizer.model.SynchronizedEvent;
import michael.linker.gestrudeid.synchronizer.model.SynchronizedEventOneModel;
import michael.linker.gestrudeid.synchronizer.model.SynchronizedEventListOfModels;

public class TextFormatter implements IFormatter {
    private static final String HEADING_SEPARATOR = "-";
    private static final String HEADING_VALUE_SEPARATOR = ":";
    private static final String HEADING_TIMESTAMP = "Timestamp";
    private final IOutputStream outputStream;

    public TextFormatter(IOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void format(SynchronizedEventOneModel synchronizedSensorModel) {
        List<String> formattedModelFields = new ArrayList<>();
        formattedModelFields.add(formatTimestamp(synchronizedSensorModel));
        formattedModelFields.add(
                formatSensorModelParameters(synchronizedSensorModel.getSensorModel()));

        final String formattedEvent = String.join(FormatterBuildConfiguration.getModelsSeparator(),
                        formattedModelFields)
                .concat(Character.toString(FormatterBuildConfiguration.getEventsSeparator()));
        outputStream.write(formattedEvent);
    }

    @Override
    public void format(SynchronizedEventListOfModels synchronizedSensorModels) {
        List<String> formattedModelFields = new ArrayList<>();
        formattedModelFields.add(formatTimestamp(synchronizedSensorModels));

        for (ASensorModel sensorModel : synchronizedSensorModels.getSensorModels()) {
            formattedModelFields.add(formatSensorModelParameters(sensorModel));
        }

        final String formattedEvent = String.join(FormatterBuildConfiguration.getModelsSeparator(),
                        formattedModelFields)
                .concat(Character.toString(FormatterBuildConfiguration.getEventsSeparator()));
        outputStream.write(formattedEvent);
    }

    private String formatSensorModelParameters(final ASensorModel sensorModel) {
        final List<String> modelParameters = new ArrayList<>();
        final Map<String, Float> parametersNamesAndValuesMap = sensorModel.getNamesAndValuesMap();
        final String sensorTypeAsString = sensorModel.getSensorType().toString();

        for (String parameterName : parametersNamesAndValuesMap.keySet()) {
            String parameterNameValueStringBuilder = sensorTypeAsString
                    + HEADING_SEPARATOR
                    + parameterName
                    + HEADING_VALUE_SEPARATOR
                    + parametersNamesAndValuesMap.get(parameterName);
            modelParameters.add(parameterNameValueStringBuilder);
        }

        return String.join(FormatterBuildConfiguration.getModelValuesSeparator(), modelParameters);
    }

    private String formatTimestamp(final SynchronizedEvent sensorEvent) {
        return HEADING_TIMESTAMP.concat(HEADING_VALUE_SEPARATOR).concat(sensorEvent.getTimestamp());
    }
}
