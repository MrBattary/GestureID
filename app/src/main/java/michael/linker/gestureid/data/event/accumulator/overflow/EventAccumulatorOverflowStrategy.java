package michael.linker.gestureid.data.event.accumulator.overflow;

public enum EventAccumulatorOverflowStrategy {
    // Flush all data from the buffer
    FLUSH,
    // Flush the oldest data
    FLUSH_OLDEST,
    // Raise error
    RAISE_ERROR
}
