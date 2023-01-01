package michael.linker.gestureid.event.buffer;

public enum EventBufferOverflowStrategy {
    // Flush all data from the buffer
    FLUSH,
    // Raise error
    RAISE_ERROR
}
