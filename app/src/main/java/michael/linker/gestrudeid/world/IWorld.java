package michael.linker.gestrudeid.world;

import michael.linker.gestrudeid.stream.output.model.AOutputStreamModel;
import michael.linker.gestrudeid.world.exception.WorldFailedException;

public interface IWorld {
    /**
     * Open new output stream for the sensors data
     *
     * @param outputStreamModel Model that contains specific data for the provided stream
     * @throws WorldFailedException If the World is unable to set a new output stream
     */
    void setNewOutputStream(AOutputStreamModel outputStreamModel) throws WorldFailedException;

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
