package michael.linker.gestureid.config;

public abstract class ConfigurationBean<T> {
    private T implementation;

    public ConfigurationBean(T defaultImplementation) {
        this.implementation = defaultImplementation;
    }

    /**
     * Implement with object.
     *
     * @param newImplementation T
     * @return new implementation.
     */
    public abstract T implement(T newImplementation);

    /**
     * Getter.
     *
     * @return current implementation.
     */
    public T getImplementation() {
        return implementation;
    }

    /**
     * Setter.
     *
     * @param newImplementation new implementation.
     */
    protected void setImplementation(T newImplementation) {
        implementation = newImplementation;
    }
}
