package michael.linker.gestureid.data.system.gate;

public interface ISystemGateListener {
    /**
     * Require auth from the subscriber.
     *
     * @return auth result.
     */
    SystemGateAuthResult requireAuth();
}
