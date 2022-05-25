package michael.linker.gestrudeid.formatter.impl;

import java.util.List;

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
        // TODO: finish it
    }

    @Override
    public void format(List<ASensorModel> sensorModels) {
        // TODO: finish it
    }
}
