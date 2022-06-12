package michael.linker.gestrudeid.stream.output.stream;

import michael.linker.gestrudeid.stream.IStream;

/**
 * The output stream of data read from the sensor
 */
public interface IOutputStream extends IStream {

    /**
     * Write text to the stream
     *
     * @param s Data as String
     */
    void write(String s);
}
