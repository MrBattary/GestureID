package michael.linker.gestureid.config;

import michael.linker.gestureid.BuildConfig;
import michael.linker.gestureid.event.buffer.overflow.EventBufferOverflowStrategy;

/**
 * Wrapper for the listener build variables
 * "build.gradle" file in the application main folder
 *
 * @see michael.linker.gestureid.event
 */
public class EventsBuildConfiguration {
    public static class Buffer {
        public static int getBufferMaxSize() {
            return BuildConfig.EVENT_BUFFER_MAX_SIZE;
        }

        public static EventBufferOverflowStrategy getBufferOverflowStrategy() {
            return BuildConfig.EVENT_BUFFER_OVERFLOW_STRATEGY;
        }
    }
}
