package michael.linker.gestureid.formatter.impl.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import michael.linker.gestureid.config.FormatterBuildConfiguration;
import michael.linker.gestureid.formatter.IFormatter;
import michael.linker.gestureid.sensor.model.ASensorModel;
import michael.linker.gestureid.stream.output.stream.IOutputStream;
import michael.linker.gestureid.synchronizer.model.SynchronizedEvent;
import michael.linker.gestureid.synchronizer.model.SynchronizedEventOneModel;
import michael.linker.gestureid.synchronizer.model.SynchronizedEventListOfModels;

public class TextFormatter implements IFormatter {
    private IOutputStream outputStream;

    /**
     * Default constructor
     */
    public TextFormatter() {
    }

    /**
     * Default constructor with stream
     * @param outputStream Provided realisation of the output stream
     */
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

    @Override
    public void setNewOutputStream(IOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private String formatSensorModelParameters(final ASensorModel sensorModel) {
        final List<String> modelParameters = new ArrayList<>();
        final Map<String, Float> parametersNamesAndValuesMap = sensorModel.getNamesAndValuesMap();
        final String sensorTypeAsString = sensorModel.getSensorType().toString();

        for (String parameterName : parametersNamesAndValuesMap.keySet()) {
            String parameterNameValueStringBuilder = sensorTypeAsString
                    + FormatterBuildConfiguration.getHeadingSeparator()
                    + parameterName
                    + FormatterBuildConfiguration.getHeadingValueSeparator()
                    + parametersNamesAndValuesMap.get(parameterName);
            modelParameters.add(parameterNameValueStringBuilder);
        }

        return String.join(FormatterBuildConfiguration.getModelValuesSeparator(), modelParameters);
    }

    private String formatTimestamp(final SynchronizedEvent sensorEvent) {
        return FormatterBuildConfiguration.getHeadingTimestamp()
                .concat(FormatterBuildConfiguration.getHeadingValueSeparator())
                .concat(sensorEvent.getTimestamp());
    }
}
