package michael.linker.gestureid.data.system.network;

public interface IPersistentSystemNetwork extends ISystemNetwork {
    /**
     * Store network to the permanent storage.
     */
    void persist();
}
