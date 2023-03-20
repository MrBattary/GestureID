package michael.linker.gestureid.data.stream.output;

import michael.linker.gestureid.data.stream.IStream;

/**
 * General output stream class for the file, memory output etc
 */
public interface IOutputStream extends IStream {
    /**
     * Writes a string and does a line break.
     *
     * @param line - Input string.
     * @throws OutputStreamException - If something goes wrong.
     */
    void writeLine(String line) throws OutputStreamException;
}
