package michael.linker.gestrudeid.stream.output.model;

import michael.linker.gestrudeid.stream.output.type.OutputStreamType;

/**
 * Basic output stream model for other model with additional data
 */
public abstract class AOutputStreamModel {
    protected final OutputStreamType streamType;

    public AOutputStreamModel(OutputStreamType streamType) {
        this.streamType = streamType;
    }

    public OutputStreamType getStreamType() {
        return streamType;
    }
}
