package michael.linker.gestureid.config;

import michael.linker.gestureid.BuildConfig;
import michael.linker.gestureid.event.buffer.mode.active.ActiveEventBuffer;
import michael.linker.gestureid.event.buffer.mode.active.IActiveEventBuffer;
import michael.linker.gestureid.event.buffer.overflow.EventBufferOverflowStrategy;

/**
 * Event buffer configuration
 *
 * @see michael.linker.gestureid.event.buffer
 */
public class EventBufferConfiguration {
    private static IActiveEventBuffer activeBuffer = null;

    public static IActiveEventBuffer getActiveBuffer() {
        if (activeBuffer == null) {
            activeBuffer = new ActiveEventBuffer();
        }
        return activeBuffer;
    }

    /**
     * Wrapper for the event buffer build variables
     */
    public static class Build {
        public static int getBufferMaxSize() {
            return BuildConfig.EVENT_BUFFER_MAX_SIZE;
        }

        public static EventBufferOverflowStrategy getBufferOverflowStrategy() {
            return BuildConfig.EVENT_BUFFER_OVERFLOW_STRATEGY;
        }
    }
}
