package michael.linker.gestureid.event.buffer.overflow;

public enum EventBufferOverflowStrategy {
    // Flush all data from the buffer
    FLUSH,
    // Flush the oldest data
    FLUSH_OLDEST,
    // Raise error
    RAISE_ERROR
}
