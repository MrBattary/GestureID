package michael.linker.gestrudeid.output.stream;

/**
 * The output stream of data read from the sensor
 */
public interface ISensorStream {

    /**
     * Write text to the stream
     *
     * @param s Data as String
     */
    void write(String s);
}
