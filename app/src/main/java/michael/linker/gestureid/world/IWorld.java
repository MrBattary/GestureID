package michael.linker.gestureid.world;

import michael.linker.gestureid.stream.output.model.AOutputStreamModel;
import michael.linker.gestureid.world.exception.WorldFailedException;

public interface IWorld {
    /**
     * Open new output stream for the sensors data
     *
     * @param outputStreamModel Model that contains specific data for the provided stream
     * @throws WorldFailedException If the World is unable to set a new output stream
     */
    void setNewOutputStream(AOutputStreamModel outputStreamModel) throws WorldFailedException;

    /**
     * Closes the open output stream, if there is no open output stream, does nothing
     */
    void closeOutputStream();

    /**
     * Pause recording of events in the output stream
     */
    void suppressRegistering();

    /**
     * Proceed recording of events in the output stream
     */
    void unsuppressRegistering();

    /**
     * Close all streams and unregister every entity
     */
    void destroy();
}
