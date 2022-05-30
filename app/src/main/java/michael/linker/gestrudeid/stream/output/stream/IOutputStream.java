package michael.linker.gestrudeid.stream.output.stream;

/**
 * The output stream of data read from the sensor
 */
public interface IOutputStream {

    /**
     * Write text to the stream
     *
     * @param s Data as String
     */
    void write(String s);
}
